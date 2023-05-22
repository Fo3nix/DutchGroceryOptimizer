package client;

import commons.*;
import server.*;

import java.util.List;

// Singleton class
public class ServerUtils {

    private static final ServerUtils INSTANCE = new ServerUtils();
    private final ProductController productController = ProductController.getInstance();

    private ServerUtils() {
    }

    public static ServerUtils getInstance() {
        return INSTANCE;
    }

    public List<Product> getAllProducts() {
        return productController.getAllProducts();
    }

}
