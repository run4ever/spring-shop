package fr.training.samples.spring.shop.exposition.order.rest;

import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.exposition.common.AbstractMapper;
import fr.training.samples.spring.shop.exposition.customer.rest.CustomerMapper;
import fr.training.samples.spring.shop.exposition.item.rest.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends AbstractMapper<OrderDto, Order> {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private CustomerMapper customerMapper;

    //sert à afficher de la BDD vers web
    @Override
    public OrderDto mapToDto(final Order entity) {
        final OrderDto dto = new OrderDto();
        dto.setOrderId(entity.getId());
        dto.setCustomer(customerMapper.mapToDto(entity.getCustomer()));
        dto.setItems(itemMapper.mapToDtoList(entity.getItems()));
        dto.setTotal(entity.getTotal());
        return dto;
    }

    //dans l'autre sens
    @Override
    public Order mapToEntity(OrderDto dto) {
        final Order entity = new Order();
        entity.setId(dto.getOrderId());
        return entity;
    }

}
