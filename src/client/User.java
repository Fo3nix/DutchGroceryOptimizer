package client;

import commons.*;

import java.util.List;

public class User {
    private final List<Product> products;
    private final ServerUtils server = ServerUtils.getInstance();

    public User() {
        this.products = server.getAllProducts();
    }


}
