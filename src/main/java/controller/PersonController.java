package controller;

import bo.PersonBO;
import helpers.ConverterEntityDto;
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
            PersonDto personDto = new ConverterEntityDto<PersonDto, Person>().Convert(PersonDto.class, person.get());
            return add(personDto);
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
    public ModelAndView add(PersonDto person)
    {
        ModelAndView modelAndView = new ModelAndView("personAdd");
        modelAndView.addObject("person", person);

        return modelAndView;
    }

    @PostMapping("/save")
    @ResponseBody
    public ModelAndView savePerson(@Valid PersonDto person, BindingResult result)
    {

        if(result.hasErrors())
        {
            return add(person);
        }

        Person personEntity = new ConverterEntityDto<Person, PersonDto>().Convert(Person.class, person);

        personBO.save(personEntity);

        return getHomePerson();
    }
}
