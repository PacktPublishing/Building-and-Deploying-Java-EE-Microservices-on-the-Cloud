package io.github.vasouv.ui.jsf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.github.vasouv.ui.entity.CoffeeOrder;

/**
 *
 * @author vasouv
 */
@Named("coffeeController")
@ApplicationScoped
public class CoffeeOrderController {

    private List<CoffeeOrder> orders;
    private Map<String, String> coffeePrices;

    private String customerName, customerEmail, customerCoffee;

    private final String SERVICE_AGGREGATOR_URI = "http://localhost:8087/PacktService/packt-service/serve/";
    Client client;
    WebTarget serviceTarget;

    @PostConstruct
    public void init() {
        orders = new ArrayList<>();
        coffeePrices = new HashMap<>();
        coffeePrices.put("Espresso", "4.9");
        coffeePrices.put("Greek", "2.3");
        coffeePrices.put("Filtered", "3.6");
        client = ClientBuilder.newClient();
        serviceTarget = client.target(SERVICE_AGGREGATOR_URI);
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public String placeOrder() {
        Response done = serviceTarget
                .queryParam("customerName", customerName)
                .queryParam("customerEmail", customerEmail)
                .queryParam("coffeeName", customerCoffee)
                .queryParam("coffeePrice", coffeePrices.get(customerCoffee))
                .request(MediaType.APPLICATION_JSON)
                .post(null);
        return "index";
    }

    public List<CoffeeOrder> getOrders() {
        List<CoffeeOrder> fromREST = serviceTarget
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<CoffeeOrder>>() {
                });
        setOrders(fromREST);
        return orders;
    }

    public void setOrders(List<CoffeeOrder> orders) {
        this.orders = orders;
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

    public String getCustomerCoffee() {
        return customerCoffee;
    }

    public void setCustomerCoffee(String customerCoffee) {
        this.customerCoffee = customerCoffee;
    }

}
