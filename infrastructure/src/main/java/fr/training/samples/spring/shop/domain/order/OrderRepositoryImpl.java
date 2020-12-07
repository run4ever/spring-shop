package fr.training.samples.spring.shop.domain.order;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order findById(final String id) {
        return orderJpaRepository.findById(id).orElseThrow(()-> new NotFoundException());
    }

    @Override
    public void save(final Order order) {
        orderJpaRepository.save(order);
    }

    @Override
    public List<Order> findAllByCustomerId(String customerId) {
        return null;
    }


}
