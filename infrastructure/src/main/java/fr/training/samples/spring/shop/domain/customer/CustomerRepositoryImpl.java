package fr.training.samples.spring.shop.domain.customer;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerRepositoryImpl(final CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Customer findById(String id) {
        return customerJpaRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void save(final Customer customer) {
        customerJpaRepository.save(customer);
    }

    @Override
    public Customer findByCustomerName(String name) {
        return customerJpaRepository.findByName(name);
    }
}
