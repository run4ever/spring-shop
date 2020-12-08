package fr.training.samples.spring.shop.order;

import fr.training.samples.spring.shop.application.order.OrderService;
import fr.training.samples.spring.shop.application.order.OrderServiceImpl;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrderServiceImpl.class})
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private CustomerRepository customerRepositoryMock;

    @MockBean
    private OrderRepository orderRepositoryMock;

    @MockBean
    private ItemRepository itemRepositoryMock;

    private Order getNewOrder() {
        final Order order = new Order();
        order.setCustomer(getCustomer());
        order.setItems(getItems());
        return order;
    }

    private List<Item> getItems() {
        final List<Item> items = new ArrayList<>();
        final Item item1 = new Item();
        item1.setDescription("item 1");
        item1.setPrice(1);
        items.add(item1);
        final Item item2 = new Item();
        item2.setDescription("item 2");
        item2.setPrice(2);
        items.add(item2);
        final Item item3 = new Item();
        item3.setDescription("item 3");
        item3.setPrice(3);
        items.add(item3);
        return items;
    }

    private Customer getCustomer() {
        final Customer customer = new Customer();
        customer.setName("Toto");
        customer.setPassword("passToto");
        return customer;
    }

    @Test
    public void find_one_customer_should_success(){
        //Given
        final String orderId = "id-order-001-001";
        final Order order = getNewOrder();
        when(orderRepositoryMock.findById(orderId)).thenReturn(order);

        //When
        final Order result = orderService.findOne(orderId);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getCustomer().getName()).isEqualTo("Toto");
        assertThat(result.getCustomer().getPassword()).isEqualTo("passToto");
        verify(orderRepositoryMock, times(1)).findById(orderId);

    }

    @Test
    public void getOrdersForCustomer_should_return_Order_list(){
        //Given
        final String customerId = "123e4567-e89b-42d3-a456-556642440000";
        final List<Order> orders = new ArrayList<>();
        orders.add(getNewOrder());
        when(orderRepositoryMock.findByCustomerId(customerId)).thenReturn(orders);

        // When
        final List<Order> expected = orderService.getOrdersForCustomer(customerId);

        // Then
        assertThat(expected).hasSize(1);
    }

    @Test
    public void addOrder_should_call_save_repositories_1_time() {
        // Given
        final String customerId = "123e4567-e89b-42d3-a456-556642440000";
        final List<String> itemIds = Arrays.asList(new String[] { "1", "2", "3" });
        when(customerRepositoryMock.findById(customerId)).thenReturn(getCustomer());
        when(itemRepositoryMock.findById(itemIds)).thenReturn(getItems());
        // When
        final Order result = orderService.addOrder(customerId, itemIds);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTotal()).isEqualTo(6);
        verify(orderRepositoryMock, times(1)).save(any());
        verify(customerRepositoryMock, times(1)).findById(customerId);
        verify(itemRepositoryMock, times(1)).findById(itemIds);
    }

}
