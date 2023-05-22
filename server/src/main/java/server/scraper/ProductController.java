package server.scraper;

import commons.Product;

import java.util.LinkedList;
import java.util.List;

public class ProductController {



    private final static ProductController INSTANCE = new ProductController();

    public static ProductController getInstance() {
        return INSTANCE;
    }

    private final HoogvlietController hoogvliet = HoogvlietController.getInstance();

    private ProductController() {
    }

    public List<Product> getAllProducts() {
        List<Product> products = new LinkedList<>();

        products.addAll(hoogvliet.getAllHoogvlietProducts());

        return products;
    }

}
