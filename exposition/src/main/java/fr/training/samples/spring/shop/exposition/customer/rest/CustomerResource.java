package fr.training.samples.spring.shop.exposition.customer.rest;

import fr.training.samples.spring.shop.application.customer.CustomerService;
import fr.training.samples.spring.shop.application.order.OrderService;
import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.domain.order.Order;
import fr.training.samples.spring.shop.exposition.order.rest.OrderDto;
import fr.training.samples.spring.shop.exposition.order.rest.OrderMapper;
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
public class CustomerResource {

    private static final Logger logger = LoggerFactory.getLogger(CustomerResource.class);
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public CustomerResource(CustomerService customerService, CustomerMapper customerMapper, OrderService orderService, OrderMapper orderMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }


    //get a customer by Id - 123e4567-e89b-42d3-a456-556642440000
    @GetMapping(value = "/customers/{id}", produces = { "application/json" })
    public CustomerDto getCustomersUsingGet(@PathVariable final String id) {
        return customerMapper.mapToDto(customerService.findOne(id));
    }

    //add a customer via postman, et récuperer id via postman
    @PostMapping(value = "/customers", consumes = { "application/json" })
    public ResponseEntity<URI> addCustomerUsingPost(@Valid @RequestBody final CustomerLightDto customerDto) {

        final Customer customer = customerMapper.mapToEntity(customerDto);
        customerService.create(customer);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //update customer, via postman, exple 123e4567-e89b-42d3-a456-556642440000
    @PutMapping(value = "/customers/{id}", consumes = { "application/json" })
    public ResponseEntity<URI> updateCustomerUsingPut(@Valid @RequestBody final CustomerDto customerDto, @PathVariable String id) {

        customerDto.setCustomerID(id);
        customerService.update(customerMapper.mapToEntity(customerDto));

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // autre méthode mais un peu moins bien : /orders  et en ReqsuetsParam customerId
    // customer id : 123e4567-e89b-42d3-a456-556642440000
    @GetMapping(value = "/customers/{customerId}/orders", produces = { "application/json" })
    public List<OrderDto> getAllOrdersUsingGet(@PathVariable final String customerId) {
        final List<Order> orders = orderService.getOrdersForCustomer(customerId);
        logger.info("Number of orders returned: {}", orders.size());
        return orderMapper.mapToDtoList(orders);
    }

}
