package com.spring.el.repository;

import com.spring.el.SpringDataJpaEclipselinkApplication;
import com.spring.el.domain.Item;
import com.spring.el.domain.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringDataJpaEclipselinkApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PersonRepository personRepository;

    private Person person;

    @Before
    public void setUp() {
        itemRepository.deleteAll();
        personRepository.deleteAll();

        person = new Person();
        person.setName("test");
        personRepository.save(person);
    }

    @Test
    public void item_should_be_saved() {
        // given
        final Item item = new Item();
        item.setCode("CODE_0001");
        item.setDisplayName("test display name");
        item.setPerson(person);

        // when
        itemRepository.save(item);

        // then
        assertThat(item.getId()).isNotNull();
        assertThat(item.getCode()).isEqualTo("CODE_0001");

        assertThat(personRepository.findOne(person.getId()).getItems())
                .extracting(Item::getCode, Item::getDisplayName)
                .contains(
                        tuple("CODE_0001", "test display name")
                );
    }
}