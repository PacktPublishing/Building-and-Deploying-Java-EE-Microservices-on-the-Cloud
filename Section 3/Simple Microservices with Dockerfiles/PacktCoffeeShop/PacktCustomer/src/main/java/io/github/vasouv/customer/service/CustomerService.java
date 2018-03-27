package io.github.vasouv.customer.service;

import io.github.vasouv.customer.entity.Customer;
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
public class CustomerService {

    @PersistenceContext(unitName = "PacktCustomerPU")
    private EntityManager em;

    public List<Customer> findCustomers() {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
        List<Customer> customers = query.getResultList();
        return customers;
    }
    
    public Customer createCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }
    

}
