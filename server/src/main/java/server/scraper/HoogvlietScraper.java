package server.scraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import commons.BaseUnit;
import commons.Product;
import commons.Promotion;
import commons.Store;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class HoogvlietScraper {

    private final static HoogvlietScraper INSTANCE = new HoogvlietScraper();

    private HttpURLConnection connection;

    private HoogvlietScraper() {
    }

    private void setUpServerConnection(int page, int pageSize) {
        try {
            URL url = new URL("https://navigator-group1.tweakwise.com/navigation/ed681b01?tn_q=&tn_p="+ page + "&tn_ps=" + pageSize + "&tn_sort=Relevantie&tn_cid=999999&CatalogPermalink=producten&CategoryPermalink=&format=json&tn_parameters=ae-productorrecipe%3Dproduct");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpServerConnection(List<String> SKUs){
        try {
            // Comma separated string of SKUs
            String SKUcommaString = "";
            for (int i = 0; i < SKUs.size(); i++) {
                SKUcommaString += SKUs.get(i);
                if (i != SKUs.size() - 1) {
                    SKUcommaString += ",";
                }
            }

            URL url = new URL("https://www.hoogvliet.com/INTERSHOP/web/WFS/org-webshop-Site/nl_NL/-/EUR/ProcessTWProducts-GetTWProductsBySkus?products=" + SKUcommaString + "&format=json");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "json/application");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
            //connection.setRequestProperty("robots", "noindex");

            var dfsjhdf = connection.getContentType();
            var pizza = connection.getRequestProperty("user-agent");

            System.out.println("pizza");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HoogvlietScraper getInstance() {
        return INSTANCE;
    }

    public List<Product> getAllHoogvlietProducts() {

       List<Product> products = getProductsGeneral();

       addAdditionalInfo(products);

       return products;
    }

    private void addAdditionalInfo(List<Product> products) {

        int begin = 0;
        int end = 10;
        while (end < products.size()) {
            List<String> SKUs = new LinkedList<>();
            List<Product> lookingAtProducts = new LinkedList<>();

            for (int i = begin; i < end; i++) {
                SKUs.add(products.get(i).getStoreSKU());
                lookingAtProducts.add(products.get(i));
            }
            setUpServerConnection(SKUs);

            String jsonString = getURLResponse();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = null;
            try {
                root = mapper.readTree(jsonString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            parseAdditionalInfoItems(root, lookingAtProducts);

            SKUs.clear();
            lookingAtProducts.clear();

            begin = end;
            end += 10;
            if(end > products.size()){
                end = products.size();
            }
        }


    }

    private String getURLResponse() {
        StringBuilder sb = new StringBuilder();
        try {


            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private void parseAdditionalInfoItems(JsonNode root, List<Product> lookingAtProducts) {
        for(JsonNode node : root){
            String sku = node.path("sku").asText();
            int discountedPrice = node.path("discountedPrice").asInt();
            String discountLabel = node.path("customAttributes").path("discountLabel").asText();

            for(Product product : lookingAtProducts){
                if(product.getStoreSKU() == sku){
                    Promotion promo = product.getPromotion();
                    promo.setDiscountedPrice(discountedPrice);
                    promo.setLabel(discountLabel);
                }
            }

        }


    }


    private List<Product> getProductsGeneral() {
        List<Product> products = new LinkedList<>();

        int page = 1;
        int pageSize = 1000;
        while (pageSize != 1) {

            String pageString = getProductsGeneralStringPage(page, pageSize);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = null;
            JsonNode items = null;
            try {
                root = mapper.readTree(pageString);
                items = root.path("items");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            // Parse JSON to Product objects
            List<Product> tempProducts = parseItems(items);

            if(tempProducts.size()==12){
                int itemsSeen = page * pageSize;
                pageSize = pageSize / 2;
                page = (itemsSeen / pageSize) + 1;
            }
            if(tempProducts.size()<1000){
                products.addAll(tempProducts);
                pageSize = 1;
            }
            else {
                products.addAll(tempProducts);
                page++;
            }

        }

        return products;
    }

    private List<Product> parseItems(JsonNode items) {
        List<Product> products = new LinkedList<>();


        for (JsonNode node : items) {

            String sku = node.path("itemno").asText();
            String name = node.path("title").asText();
            String brand = node.path("brand").asText();
            double price = node.path("price").asDouble();
            String category = node.path("category").asText();
            String baseUnit = node.path("attributes").path(1).path("values").path(0).asText();
            BaseUnit baseUnitEnum = BaseUnit.valueOf("UNKNOWN");

            try {
                if(baseUnit.equals("stuks") || baseUnit.equals("stuk") || baseUnit.equals("pak") || baseUnit.equals("rol")){
                    baseUnitEnum = BaseUnit.valueOf("PIECE");
                }
                else if (baseUnit.equals("kilo")){
                    baseUnitEnum = BaseUnit.valueOf("KILOGRAM");
                }
                else{
                    baseUnitEnum = BaseUnit.valueOf(baseUnit.toUpperCase());
                }
            } catch (Exception e) {
                System.out.println("BaseUnit not found at "+ sku +": " + baseUnit);
            }

            int quantity = node.path("attributes").path(2).path("values").path(0).asInt();

            Product product = new Product(name, sku, baseUnitEnum, quantity, Store.HOOGVLIET, price, brand, category, new Promotion());
            products.add(product);
        }

        return products;
    }

    private String getProductsGeneralStringPage(int page, int pageSize) {
        setUpServerConnection(page, pageSize);
        return getURLResponse();
    }

}
