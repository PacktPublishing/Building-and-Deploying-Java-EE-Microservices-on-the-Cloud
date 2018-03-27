package io.github.vasouv.service.entity;

/**
 *
 * @author vasouv
 */
public class CoffeeOrder {

    private String id;
    private String coffeeName;
    private String price;
    private String customerId;

    public CoffeeOrder() {
    }

    public CoffeeOrder(String id, String coffeeName, String price, String customerId) {
        this.id = id;
        this.coffeeName = coffeeName;
        this.price = price;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}
