package io.github.vasouv.order.rest;

import io.github.vasouv.order.entity.CoffeeOrder;
import io.github.vasouv.order.service.CoffeeOrderService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author vasouv
 */
@Path("/orders")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoffeeOrderResource {

    @Inject
    private CoffeeOrderService coffeeOrderService;

    @GET
    public Response getCoffeeOrders() {
        return Response.ok(coffeeOrderService.findCoffeeOrders()).build();
    }

    @POST
    public Response createCoffeeOrder(CoffeeOrder newCoffeOrder) {
        return Response.ok(coffeeOrderService.createCoffeeOrder(newCoffeOrder)).build();
    }

}
