package server;

import commons.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
