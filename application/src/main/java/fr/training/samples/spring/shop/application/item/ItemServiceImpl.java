package fr.training.samples.spring.shop.application.item;

import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    @Override
    public Item addItem(final Item item) {
        itemRepository.save(item);
        return item;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
