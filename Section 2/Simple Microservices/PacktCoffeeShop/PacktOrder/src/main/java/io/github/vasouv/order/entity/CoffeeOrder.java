package io.github.vasouv.order.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.UuidGenerator;

/**
 *
 * @author vasouv
 */
@Entity
@Table(name = "orders")
@NamedQueries({
    @NamedQuery(name = "CoffeeOrders.findAll", query = "SELECT co FROM CoffeeOrder co")
})
public class CoffeeOrder {

    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator(name = "uuid")
    private String id;

    @Column(name = "coffeeName")
    private String coffeeName;

    @Column(name = "price")
    private String price;

    @Column(name = "customerId")
    private String customerId;

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
