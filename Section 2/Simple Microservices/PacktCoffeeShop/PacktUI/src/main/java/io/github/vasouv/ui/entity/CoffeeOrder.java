package io.github.vasouv.ui.entity;

/**
 *
 * @author vasouv
 */
public class CoffeeOrder {

    private String customerName;
    private String customerEmail;
    private String coffeeName;
    private String coffeePrice;
    private String customerID;

    public CoffeeOrder() {
    }

    public CoffeeOrder(String customerName, String customerEmail, String coffeeName, String coffeePrice, String customerID) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.coffeeName = coffeeName;
        this.coffeePrice = coffeePrice;
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getCoffeePrice() {
        return coffeePrice;
    }

    public void setCoffeePrice(String coffeePrice) {
        this.coffeePrice = coffeePrice;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    

}
