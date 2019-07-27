package bo;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.*;
import java.util.List;
import java.util.Optional;

@Component
public class PersonBO
{
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PhotoRepository photoRepository;

    public void testJpaInsert()
    {
        Person person = new Person();
        person.setName("Luciano");
        person.setCpf("12345678901");

        personRepository.save(person);

        Photo photo1 = new Photo();
        photo1.setLink("1");
        photo1.setPerson(person);

        photoRepository.save(photo1);

        Photo photo2 = new Photo();
        photo2.setLink("2");
        photo2.setPerson(person);

        photoRepository.save(photo2);
    }

    public List<Person> testJpaList()
    {
        return personRepository.findAll();
    }

    public void testJpaDelete()
    {
        personRepository.deleteAll();
    }

    public void save(Person person)
    {
        personRepository.save(person);
    }

    public List<Person> findAll()
    {
        return personRepository.findAll();
    }

    public Optional<Person> findOne(long id)
    {
        return personRepository.findById(id);
    }

    public void delete(Person person)
    {
        personRepository.delete(person);
    }
}
