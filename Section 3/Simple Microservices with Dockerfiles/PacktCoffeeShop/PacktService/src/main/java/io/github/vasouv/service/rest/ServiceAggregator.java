package io.github.vasouv.service.rest;

import io.github.vasouv.service.entity.CoffeeOrder;
import io.github.vasouv.service.entity.CompleteCoffeeOrder;
import io.github.vasouv.service.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author vasouv
 */
@ApplicationScoped
@Path("serve")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServiceAggregator {

    private final String CUSTOMER_SERVICE_URI = "http://customer:8080/PacktCustomer/packt-customers";
    private final String ORDER_SERVICE_URI = "http://order:8080/PacktOrder/packt-orders";

    Client client;
    WebTarget customerTarget;
    WebTarget orderTarget;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        customerTarget = client.target(CUSTOMER_SERVICE_URI);
        orderTarget = client.target(ORDER_SERVICE_URI);
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    @GET
    public Response findAllOrders() {

        List<Customer> customers = customerTarget
                .path("customers")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Customer>>() {});

        List<CoffeeOrder> coffeeOrders = orderTarget
                .path("orders")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<CoffeeOrder>>() {});

        List<CompleteCoffeeOrder> completeCoffeeOrders = new ArrayList<>();
        for (CoffeeOrder coffeeOrder : coffeeOrders) {
            for (Customer customer : customers) {
                if (coffeeOrder.getCustomerId().equals(customer.getId())) {
                    completeCoffeeOrders
                            .add(new CompleteCoffeeOrder(customer.getFullName(),
                                    customer.getEmail(),
                                    coffeeOrder.getCoffeeName(),
                                    coffeeOrder.getPrice(),
                                    coffeeOrder.getCustomerId()));
                    break;
                }
            }
        }
        GenericEntity<List<CompleteCoffeeOrder>> list = new GenericEntity<List<CompleteCoffeeOrder>>(completeCoffeeOrders) {
        };
        return Response.ok(list).build();
    }

    @POST
    public Response createNewOrder(@QueryParam("customerName") String customerName,
            @QueryParam("customerEmail") String customerEmail,
            @QueryParam("coffeeName") String coffeeName,
            @QueryParam("coffeePrice") String coffeePrice) {

        Customer customer = new Customer(null, customerName, customerEmail);

        Response responseCustomer = customerTarget
                .path("customers")
                .request()
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON));

        Customer savedCustomer = responseCustomer.readEntity(Customer.class);

        CoffeeOrder coffeeOrder = new CoffeeOrder(null, coffeeName, coffeePrice, savedCustomer.getId());

        Response responseCoffeeOrder = orderTarget
                .path("orders")
                .request()
                .post(Entity.entity(coffeeOrder, MediaType.APPLICATION_JSON));

        CoffeeOrder savedCoffeeOrder = responseCoffeeOrder.readEntity(CoffeeOrder.class);

        return Response.ok(new CompleteCoffeeOrder(savedCustomer.getFullName(),
                savedCustomer.getEmail(),
                savedCoffeeOrder.getCoffeeName(),
                savedCoffeeOrder.getPrice(),
                savedCustomer.getId()))
                .build();

    }

}
