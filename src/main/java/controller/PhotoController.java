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
import java.util.*;

@Controller
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    PhotoBO photoBO;

    @Autowired
    PersonBO personBO;

    private ModelAndView GetHomePerson()
    {
        ModelAndView modelAndView =  new ModelAndView("redirect:/person");
        return modelAndView;
    }

    private ModelAndView SetModelView(String viewName, Photo photo)
    {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("photo", photo);

        return modelAndView;
    }

    private ModelAndView SetModelViewList(String viewName, List<Photo> photos)
    {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("photos", photos);

        return modelAndView;
    }

    @GetMapping("/upload/{id}")
    public ModelAndView upload(@PathVariable("id") Long id) {

        Optional<Person> person = personBO.FindOne(id);
        if (person != null) {
            return SetModelView("photoAdd", photoBO.CreatePhotoWithPerson(person.get()));
        }
        else {
            return SetModelView("person", null);
        }
    }

    @GetMapping("/all/{id}")
    public ModelAndView viewAll(@PathVariable("id") Long id) {

        Optional<Person> person = personBO.FindOne(id);
        if (person != null) {
            List<Photo> photos = new ArrayList<>(person.get().getPhotos());
            photos = photoBO.FixPhotos(photos);

            return SetModelViewList("photoView", photos);
        }
        else {
            return SetModelView("person", null);
        }
    }

    @PostMapping(value = "/save", consumes = "multipart/form-data")
    @ResponseBody
    public ModelAndView SavePhoto(@RequestPart("file") MultipartFile file, @Valid Photo photo, BindingResult result) throws IOException {
        if(result.hasErrors()) {
            return GetHomePerson();
        }

        photoBO.Save(photo, file);

        return GetHomePerson();
    }
}
