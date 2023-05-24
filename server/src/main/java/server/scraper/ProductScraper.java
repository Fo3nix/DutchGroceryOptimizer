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

    private final List<Product> products = new LinkedList<>();

    private ProductScraper() {
    }

    public List<Product> getAllProducts() {

        products.addAll(hoogvliet.getAllHoogvlietProducts());

        return products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
