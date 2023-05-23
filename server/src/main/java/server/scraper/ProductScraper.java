package server.scraper;

import commons.Product;

import java.util.LinkedList;
import java.util.List;

public class ProductScraper {

    private final static ProductScraper INSTANCE = new ProductScraper();

    public static ProductScraper getInstance() {
        return INSTANCE;
    }

    private final HoogvlietScraper hoogvliet = HoogvlietScraper.getInstance();

    private ProductScraper() {
    }

    public List<Product> getAllProducts() {
        List<Product> products = new LinkedList<>();

        products.addAll(hoogvliet.getAllHoogvlietProducts());

        return products;
    }

}
