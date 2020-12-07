package fr.training.samples.spring.shop.exposition.order.rest;

import fr.training.samples.spring.shop.application.order.OrderService;
import fr.training.samples.spring.shop.domain.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderResource {

    private static final Logger logger = LoggerFactory.getLogger(OrderResource.class);

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderResource(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    // c'est mieux de faire customer/{customerId}/orders et de mettre dans CustromerService
    // autre méthode mais un peu moins bien : /orders  et en ReqsuetsParam customerId
    // customer id : 123e4567-e89b-42d3-a456-556642440000
    @GetMapping(value = "/orders/{customerId}", produces = { "application/json" })
    public List<OrderDto> getAllOrdersUsingGet(@PathVariable final String customerId) {
        final List<Order> orders = orderService.getOrdersForCustomer(customerId);
        logger.info("Number of orders returned: {}", orders.size());
        return orderMapper.mapToDtoList(orders);
    }

    //ajouter un order sur un customer via postman avec OrderLightDto en entrée
    //"customerId" : "123e4567-e89b-42d3-a456-556642440000",
    //"itemIds" : ["123e4567-e89b-42d3-a456-556642440001","123e4567-e89b-42d3-a456-556642440002","123e4567-e89b-42d3-a456-556642440003"]
    //pour vérifier : http://localhost:8080/api/orders/123e4567-e89b-42d3-a456-556642440000
    @PostMapping(value = "/orders", consumes = { "application/json" })
    public ResponseEntity<URI> addOrderUsingPost(@Valid @RequestBody final OrderLightDto orderDto) {
        final Order order = orderService.addOrder(orderDto.getCustomerId(),orderDto.getItemIds());
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }




}
