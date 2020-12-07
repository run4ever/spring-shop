package fr.training.samples.spring.shop.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<Item, String> {

}
