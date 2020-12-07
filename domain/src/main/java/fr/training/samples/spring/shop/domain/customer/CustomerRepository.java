package fr.training.samples.spring.shop.domain.customer;

public interface CustomerRepository {

	Customer findById(String id);

	void save(Customer customer);

	Customer findByCustomerName(String name10);
}
