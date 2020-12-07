package fr.training.samples.spring.shop.item;

import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import org.assertj.core.internal.IterableElementComparisonStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void find_item_should_success(){
        //Given

        //When
       final Item resultat = itemRepository.findById("123e4567-e89b-42d3-a456-556642440001");

       //Then
        assertThat(resultat).isNotNull();
    }


    @Test
    public void save_item_should_success(){
        //Given
        final Item item = new Item();
        item.setPrice(10);
        item.setDescription("nouvel item");
        item.setId("id-nouvel-item-01");
        item.setVersion(0);

        //When
        itemRepository.save(item);
        final Item result = itemRepository.findById(item.getId());

        //Then
        assertThat(itemRepository.findById(item.getId())).isNotNull();
        assertThat(result.getDescription()).isEqualTo("nouvel item");
    }

    @Test
    public void findAll_should_success(){
        //Given

        //When
        final List<Item> result = itemRepository.findAll();

        //Then
        assertThat(result).isNotNull();
        assertThat(result.size()).isGreaterThan(0);
        System.out.println("il y a "+result.size()+" item");
    }

}
