package com.uniritter.upphotos;

import model.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTests {

    @Autowired
    PersonRepository repository;
    Person person;

    @Before
    public void SetClassTest()
    {
        person = new Person();
        person.setCpf("12345678901");
        person.setName("Teste");
    }

    @Test
    public void TestSavePerson()
    {
        repository.save(person);
        Optional<Person> personGet = repository.findById(person.getId());

        assertNotNull(personGet);
        assertEquals(person.getName(), personGet.get().getName());
        assertEquals(person.getCpf(), personGet.get().getCpf());
    }

    @Test
    public void TestDeletePerson()
    {
        repository.save(person);
        repository.delete(person);

        assertNotNull(person);
    }

    @Test
    public void TestFindAllPerson()
    {
        repository.save(person);
        List<Person> persons = repository.findAll();

        assertTrue(persons != null && persons.size() > 0);
    }
}
