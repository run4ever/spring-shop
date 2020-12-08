package fr.training.samples.spring.shop.customer;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void find_customer_in_repo_should_success(){
        //Given
        final String customerId = "123e4567-e89b-42d3-a456-556642440000";

        // When
        final Customer resultat = customerRepository.findById(customerId);

        //Then
        assertThat(resultat).isNotNull();
    }

    @Test
    public void save_customer_in_repo_should_success(){
        //Given
        final String customerName = "name100";
        final String customerPass = "azerty";
        final Customer customer = new Customer();
        customer.setName(customerName);
        customer.setPassword(customerPass);
        customer.setId("id-client-cree-a-la-main-01");

        //When
        customerRepository.save(customer);

        //Then
        assertThat(customerRepository.findById(customer.getId())).isNotNull();

    }


}
