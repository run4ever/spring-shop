package fr.training.samples.spring.shop.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<Customer, String> {
    Customer findByName(String name);
}
