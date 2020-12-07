package fr.training.samples.spring.shop.application.customer;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public Customer create(final Customer customer) {

        final Customer existingCustomer = customerRepository.findByCustomerName(customer.getName());
        if(existingCustomer != null){
            throw new AlreadyExistingException("A customer with this name already exists");
        }
        customerRepository.save(customer);

        return customer;
    }

    @Transactional( readOnly = true)
    @Override
    public Customer findOne(String customerId) {
        return customerRepository.findById(customerId);
    }
}
