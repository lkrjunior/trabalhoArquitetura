package controller;

import bo.*;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    PhotoBO photoBO;

    @Autowired
    PersonBO personBO;

    private ModelAndView SetModelView(String viewName, Photo photo)
    {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("photo", photo);

        return modelAndView;
    }

    private Photo CreatePhotoWithPerson(Person person)
    {
        Photo photo = new Photo();
        photo.setPerson(person);

        return photo;
    }

    @GetMapping("/upload/{id}")
    public ModelAndView upload(@PathVariable("id") Long id) {

        Optional<Person> person = personBO.FindOne(id);
        if (person != null) {
            return SetModelView("photoAdd", CreatePhotoWithPerson(person.get()));
        }
        else {
            return SetModelView("person", null);
        }
    }

    @GetMapping("/all/{id}")
    public ModelAndView viewAll(@PathVariable("id") Long id) {

        Optional<Person> person = personBO.FindOne(id);
        if (person != null) {
            return SetModelView("photoView", CreatePhotoWithPerson(person.get()));
        }
        else {
            return SetModelView("person", null);
        }
    }

    @PostMapping(value = "/save", consumes = "multipart/form-data")
    @ResponseBody
    public ModelAndView SavePhoto(@RequestPart("file") MultipartFile file, @Valid Photo photo, BindingResult result) {
        if(result.hasErrors()) {
            return SetModelView("person", null);
        }

        //personBO.Save(person);

        return SetModelView("person", null);
    }
}
