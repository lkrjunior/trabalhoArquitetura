package com.uniritter.upphotos;

import bo.*;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import repository.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonBOTests
{
    @MockBean(name="personRepository")
    private PersonRepository personRepository;

    @Autowired
    private PersonBO personBO;

    private Person person;

    @Before
    public void setup()
    {
        person = new Person();
        person.setCpf("123");
        person.setName("Test");
    }

    @Test
    public void save()
    {
        Person personResponse = person;
        when(personRepository.save(any(Person.class))).thenReturn(personResponse);

        personBO.save(person);

        assert(personResponse != null);
    }

    @Test
    public void findAll()
    {
        Person personResponse = person;
        List<Person> listPersonsResponse = new ArrayList<>();
        listPersonsResponse.add(personResponse);

        when(personRepository.findAll()).thenReturn(listPersonsResponse);

        List<Person> listPersonsExpected = personBO.findAll();

        assert(listPersonsExpected.size() > 0);
    }

    @Test
    public void findOne()
    {
        Optional<Person> personResponse;
        personResponse = Optional.of(person);

        when(personRepository.findById(any(Long.class))).thenReturn(personResponse);

        Optional<Person> personExpected = personBO.findOne(1);

        assert(personExpected.isPresent());
    }

    @Test
    public void delete()
    {
        Person personResponse = person;
        doNothing().when(personRepository).delete(any(Person.class));

        personBO.delete(person);

        assert(personResponse != null);
    }
}
