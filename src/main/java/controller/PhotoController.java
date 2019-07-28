package controller;

import bo.*;
import helpers.ConverterEntityDto;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    PhotoBO photoBO;

    @Autowired
    PersonBO personBO;

    private ModelAndView getHomePerson()
    {
        return new ModelAndView("redirect:/person");
    }

    private ModelAndView setModelView(String viewName, Photo photo)
    {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("photo", photo);

        return modelAndView;
    }

    private ModelAndView setModelViewList(String viewName, List<Photo> photos)
    {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("photos", photos);

        return modelAndView;
    }

    @GetMapping("/upload/{id}")
    public ModelAndView upload(@PathVariable("id") Long id) {

        Optional<Person> person = personBO.findOne(id);
        if (person.isPresent())
        {
            return setModelView("photoAdd", photoBO.createPhotoWithPerson(person.get()));
        }
        else
        {
            return setModelView("person", null);
        }
    }

    @GetMapping("/all/{id}")
    public ModelAndView viewAll(@PathVariable("id") Long id) {

        Optional<Person> person = personBO.findOne(id);
        if (person.isPresent())
        {
            List<Photo> photos = new ArrayList<>(person.get().getPhotos());
            photos = photoBO.fixPhotos(photos);

            ModelAndView viewForShowPhotos = setModelViewList("photoView", photos);
            viewForShowPhotos.addObject("titlePage", person.get().getName() + "\'s Photos");
            return viewForShowPhotos;
        }
        else
        {
            return setModelView("person", null);
        }
    }

    @PostMapping(value = "/save", consumes = "multipart/form-data")
    @ResponseBody
    public ModelAndView savePhoto(@RequestPart("file") MultipartFile file, @Valid PhotoDto photo, BindingResult result) throws IOException {
        if(result.hasErrors())
        {
            return getHomePerson();
        }

        Photo photoEntity = new ConverterEntityDto<Photo, PhotoDto>().Convert(Photo.class, photo);

        photoBO.save(photoEntity, file);

        return getHomePerson();
    }
}
