package com.uniritter.upphotos;

import model.Person;
import model.Photo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import repository.PersonRepository;
import repository.PhotoRepository;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PhotoRepositoryTests
{
    @Autowired
    PersonRepository repositoryPerson;

    @Autowired
    PhotoRepository repositoryPhoto;

    Person person;
    Photo photo;

    @Before
    public void SetClassTest()
    {
        person = new Person();
        person.setCpf("12345678901");
        person.setName("Teste");
    }

    private Photo GetPhotoModel()
    {
        photo = new Photo();
        photo.setPerson(person);
        photo.setLink("http://www.dropbox.com");

        return photo;
    }

    @Test
    public void TestSavePhoto()
    {
        repositoryPerson.save(person);
        photo = GetPhotoModel();
        repositoryPhoto.save(photo);
        Optional<Photo> photoGet = repositoryPhoto.findById(photo.getId());

        assertNotNull(photoGet);
        assertEquals(photo.getPerson(), photoGet.get().getPerson());
        assertEquals(photo.getLink(), photoGet.get().getLink());
    }

    @Test
    public void TestDeletePhoto()
    {
        repositoryPerson.save(person);
        photo = GetPhotoModel();
        repositoryPhoto.save(photo);
        repositoryPhoto.delete(photo);

        assertNotNull(photo);
    }

    @Test
    public void TestFindAllPhoto()
    {
        repositoryPerson.save(person);
        photo = GetPhotoModel();
        repositoryPhoto.save(photo);
        List<Photo> photos = repositoryPhoto.findAll();

        assertTrue(photos != null && photos.size() > 0);
    }
}
