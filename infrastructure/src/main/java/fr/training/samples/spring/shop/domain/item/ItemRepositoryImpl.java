package fr.training.samples.spring.shop.domain.item;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final ItemJpaRepository itemJpaRepository;

    public ItemRepositoryImpl(ItemJpaRepository itemJpaRepository) {
        this.itemJpaRepository = itemJpaRepository;
    }

    @Override
    public Item findById(final String itemId) {
        return itemJpaRepository.findById(itemId).orElseThrow(() -> new NotFoundException());
    }

    @Override
    public List<Item> findById(final List<String> ids) {
        return itemJpaRepository.findAllById(ids);
    }

    @Override
    public void save(Item item) {
        itemJpaRepository.save(item);
    }

    @Override
    public List<Item> findAll() {
        return itemJpaRepository.findAll();
    }
}
