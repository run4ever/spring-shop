package fr.training.samples.spring.shop.customer;

import fr.training.samples.spring.shop.application.customer.AlreadyExistingException;
import fr.training.samples.spring.shop.application.customer.CustomerService;
import fr.training.samples.spring.shop.application.customer.CustomerServiceImpl;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={CustomerServiceImpl.class})
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepositoryMock;

    @Test
    public void find_one_customer_should_success(){
        //Given
        final String customerId = "id-customer-bidon-001";
        final Customer customer = new Customer();
        customer.setName("Toto");
        customer.setPassword("passToto");
        when(customerRepositoryMock.findById(customerId)).thenReturn(customer);

        //When
        final Customer result = customerService.findOne(customerId);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Toto");
        assertThat(result.getPassword()).isEqualTo("passToto");
    }

    @Test
    public void create_customer_should_success_if_not_exists(){
        //Given
        final Customer customer = new Customer();
        customer.setPassword("pass10");
        customer.setName("name10");
        when(customerRepositoryMock.findByCustomerName("name10")).thenReturn(null);

        //When
        final Customer result = customerService.create(customer);

        //Then
        assertThat(result).isNotNull();
        verify(customerRepositoryMock, times(1)).save(customer);
    }

    @Test
    public void create_customer_should_fail_if_exists(){
        //Given
        final Customer customer = new Customer();
        customer.setPassword("pass10");
        customer.setName("name10");
        when(customerRepositoryMock.findByCustomerName("name10")).thenReturn(customer);

        //When
        Customer result;
        try{
            result = customerService.create(customer);
        }catch(final Exception e){
            assertThat(e).isInstanceOf(AlreadyExistingException.class);
        }

        //Then
        verify(customerRepositoryMock, never()).save(customer);
    }


}
