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
