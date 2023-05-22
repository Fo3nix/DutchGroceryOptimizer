package commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Product {

    private String id;
    private String name;
    private String imageURL;
    private String storeSKU;
    private BaseUnit baseUnit;
    private int quantityOfBaseUnit;
    private Store store;
    private double price;
    private String brand;
    private String category;
    private Promotion promotion;

    private static String generateID(String storeSKU, Store store){
        return store.toString() + storeSKU;
    }

    /**
     * Constructor for Product
     */
    Product() {
    }

    public Product(String name, String storeSKU, BaseUnit baseUnit, int quantityOfBaseUnit, Store store, double price, String brand, String category, Promotion promotion) {
        this.id = generateID(storeSKU, store);
        this.name = name;
        this.storeSKU = storeSKU;
        this.baseUnit = baseUnit;
        this.quantityOfBaseUnit = quantityOfBaseUnit;
        this.store = store;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.promotion = promotion;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreSKU() {
        return storeSKU;
    }

    public void setStoreSKU(String storeSKU) {
        this.storeSKU = storeSKU;
    }

    public BaseUnit getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(BaseUnit baseUnit) {
        this.baseUnit = baseUnit;
    }

    public int getQuantityOfBaseUnit() {
        return quantityOfBaseUnit;
    }

    public void setQuantityOfBaseUnit(int quantityOfBaseUnit) {
        this.quantityOfBaseUnit = quantityOfBaseUnit;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantityOfBaseUnit == product.quantityOfBaseUnit && Double.compare(product.price, price) == 0 && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(storeSKU, product.storeSKU) && baseUnit == product.baseUnit && store == product.store && Objects.equals(brand, product.brand) && Objects.equals(category, product.category) && Objects.equals(promotion, product.promotion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, storeSKU, baseUnit, quantityOfBaseUnit, store, price, brand, category, promotion);
    }
}
