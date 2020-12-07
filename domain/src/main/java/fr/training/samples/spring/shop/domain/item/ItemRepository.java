package fr.training.samples.spring.shop.domain.item;

import java.util.List;

public interface ItemRepository {

    Item findById(String id);

    List<Item> findById(List<String> ids);

    void save(Item item);

    List<Item> findAll();



}
