package io.github.vasouv.order.service;

import io.github.vasouv.order.entity.CoffeeOrder;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author vasouv
 */
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class CoffeeOrderService {

    @PersistenceContext(unitName = "PacktOrderPU")
    private EntityManager em;

    public List<CoffeeOrder> findCoffeeOrders() {
        TypedQuery<CoffeeOrder> query = em.createNamedQuery("CoffeeOrders.findAll", CoffeeOrder.class);
        List<CoffeeOrder> coffeeOrders = query.getResultList();
        return coffeeOrders;
    }

    public CoffeeOrder createCoffeeOrder(CoffeeOrder coffeeOrder) {
        em.persist(coffeeOrder);
        return coffeeOrder;
    }

}
