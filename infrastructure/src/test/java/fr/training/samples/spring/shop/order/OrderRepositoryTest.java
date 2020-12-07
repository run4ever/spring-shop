package fr.training.samples.spring.shop.order;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void findOrderById_should_success(){
        //Given
        final String orderId = "123e4567-189b-42d3-a456-556642440000";

        //When
        final Order result = orderRepository.findById(orderId);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getTotal()).isEqualTo(30);
    }

    @Test
    public void saveOrder_should_success(){
        //Given
        final Order newOrder = new Order();
        final Customer customer = customerRepository.findById("223e4567-e89b-42d3-a456-556642440000");
        final Item item1 = itemRepository.findById("123e4567-e89b-42d3-a456-556642440001");
        final Item item2 = itemRepository.findById("123e4567-e89b-42d3-a456-556642440002");

        newOrder.setId("id-nouvel-order-123456");
        newOrder.setCustomer(customer);
        newOrder.addItem(item1);
        newOrder.addItem(item2);

        //When
        orderRepository.save(newOrder);

        //Then
        assertThat(orderRepository.findById(newOrder.getId())).isNotNull();
        assertThat(orderRepository.findById(newOrder.getId()).getCustomer()).isEqualTo(customer);

    }

}
