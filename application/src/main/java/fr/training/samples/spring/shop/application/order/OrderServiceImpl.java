package fr.training.samples.spring.shop.application.order;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.domain.order.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final ItemRepository itemRepository;

    public OrderServiceImpl(final OrderRepository orderRepository, final CustomerRepository customerRepository,
                            final ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    @Override
    public Order addOrder(final String CustomerId, final List<String> itemIds) {
        final Customer customer = customerRepository.findById(CustomerId);

        final Order order = new Order();
        order.setCustomer(customer);

        final List<Item> items = itemRepository.findById(itemIds);
        for (final Item item : items) {
            order.addItem(item);
            order.setTotal(Integer.sum(order.getTotal(), item.getPrice()));
        }
        orderRepository.save(order);
        return order;
    }

    @Transactional(readOnly = true)
    @Override
    public Order findOne(final String orderId) {
        return orderRepository.findById(orderId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getOrdersForCustomer(final String customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }

}