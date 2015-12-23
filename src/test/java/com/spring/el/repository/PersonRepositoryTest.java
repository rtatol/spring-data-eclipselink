package com.spring.el.repository;

import com.spring.el.SpringDataJpaEclipselinkApplication;
import com.spring.el.domain.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringDataJpaEclipselinkApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        personRepository.deleteAll();
    }

    @Test
    public void person_should_be_saved() {
        // given
        final Person person = new Person();
        person.setName("test");

        // when
        personRepository.save(person);

        // then
        assertThat(person.getId()).isNotNull();
        assertThat(person.getName()).isEqualTo("test");
        assertThat(personRepository.findOne(person.getId())).isNotNull();
    }


    @Test
    public void should_find_saved_person_by_name() {
        // given
        final Person personToSave = new Person();
        personToSave.setName("test");
        personRepository.save(personToSave);

        // when
        final List<Person> persons = personRepository.findByName("test");

        // then
        assertThat(persons)
                .extracting(Person::getName)
                .containsOnly("test");
    }

}