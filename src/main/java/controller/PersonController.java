package controller;

import bo.PersonBO;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonBO personBO;

    private ModelAndView getHomePerson()
    {
        return new ModelAndView("redirect:/person");
    }

    @GetMapping("")
    public ModelAndView findAll() {

        ModelAndView modelAndView = new ModelAndView("person");
        modelAndView.addObject("persons", personBO.findAll());

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {

        Optional<Person> person = personBO.findOne(id);
        if (person.isPresent())
        {
            return add(person.get());
        }
        else
        {
            return getHomePerson();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {

        Optional<Person> person = personBO.findOne(id);
        if (person.isPresent())
        {
            personBO.delete(person.get());
        }

        return getHomePerson();
    }

    @GetMapping("/add")
    public ModelAndView add(Person person) {

        ModelAndView modelAndView = new ModelAndView("personAdd");
        modelAndView.addObject("person", person);

        return modelAndView;
    }

    @PostMapping("/save")
    @ResponseBody
    public ModelAndView savePerson(@Valid Person person, BindingResult result)
    {
        if(result.hasErrors()) {
            return add(person);
        }

        personBO.save(person);

        return getHomePerson();
    }
}
