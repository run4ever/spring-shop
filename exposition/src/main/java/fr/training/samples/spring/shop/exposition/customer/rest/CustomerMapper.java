package fr.training.samples.spring.shop.exposition.customer.rest;

import fr.training.samples.spring.shop.domain.customer.Customer;
import fr.training.samples.spring.shop.exposition.common.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper extends AbstractMapper<CustomerDto, Customer> {
    @Override
    public CustomerDto mapToDto(final Customer entity) {
        final CustomerDto dto = new CustomerDto();
        dto.setCustomerID(entity.getId());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    @Override
    public Customer mapToEntity(CustomerDto dto) {
        final Customer entity = new Customer();
        entity.setId(dto.getCustomerID());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public Customer mapToEntity(CustomerLightDto dto) {
        final Customer entity = new Customer();
        entity.setPassword(dto.getPassword());
        entity.setName(dto.getName());
        return entity;
    }


}
