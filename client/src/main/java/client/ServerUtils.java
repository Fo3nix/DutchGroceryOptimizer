package client;


import commons.Product;
import server.scraper.*;

import java.util.List;

// Singleton class
public class ServerUtils {

    private static final ServerUtils INSTANCE = new ServerUtils();
    private final ProductScraper productScraper = ProductScraper.getInstance();

    private ServerUtils() {
    }

    public static ServerUtils getInstance() {
        return INSTANCE;
    }

    public List<Product> getAllProducts() {
        return productScraper.getAllProducts();
    }

}
