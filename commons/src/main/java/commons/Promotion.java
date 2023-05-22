package commons;

public class Promotion {

    private boolean isActive;
    private String label;
    private PromotionType type;
    private double discountedPrice;
    private BaseUnit baseUnit;
    private int quantityOfBaseUnit;

    /**
     * Constructor for Promotion
     */
    public Promotion() {
        this.isActive = false;
    }

    /**
     * Constructor for Promotion
     * @param label
     * @param type
     * @param discountedPrice
     * @param baseUnit
     * @param quantityOfBaseUnit
     */
    public Promotion(boolean isActive, String label, PromotionType type, double discountedPrice, BaseUnit baseUnit, int quantityOfBaseUnit) {
        this.isActive = isActive;
        this.label = label;
        this.type = type;
        this.discountedPrice = discountedPrice;
        this.baseUnit = baseUnit;
        this.quantityOfBaseUnit = quantityOfBaseUnit;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public PromotionType getType() {
        return type;
    }

    public void setType(PromotionType type) {
        this.type = type;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
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
}
