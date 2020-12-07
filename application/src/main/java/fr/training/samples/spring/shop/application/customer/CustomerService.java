package fr.training.samples.spring.shop.application.customer;

import fr.training.samples.spring.shop.domain.customer.Customer;

public interface CustomerService {

    /**
     * Create a new customer
     * @param customer the Customer to create
     * @return the created Customer
     */
    public Customer create(Customer customer);

    /**
     * Retrieve a customer according to the given identifier.
     * @param customerId the customer identifier
     * @return the retrieved Customer
     */
    public Customer findOne(String customerId);

}
