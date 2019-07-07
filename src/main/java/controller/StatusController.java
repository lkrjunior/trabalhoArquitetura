package controller;

import bo.PersonBO;
import bo.VersionBO;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/")
public class StatusController {
    @Autowired
    VersionBO versionBO;

    @Autowired
    PersonBO personBO;

    @GetMapping("/status")
    @ResponseBody
    public String GetStatus()
    {
        Version version = versionBO.GetVersion();
        return version.getValue();
    }

    @GetMapping("")
    @ResponseBody
    public ModelAndView Home()
    {
        return new ModelAndView("home");
    }

    @GetMapping("/testjpainsert")
    @ResponseBody
    public String TestJpaInsert()
    {
        personBO.TestJpaInsert();
        return "Insert Ok";
    }

    @GetMapping("/testjpalist")
    @ResponseBody
    public List<Person> TestJpaList()
    {
        return personBO.TestJpaList();
    }

    @GetMapping("/testjpadelete")
    @ResponseBody
    public String TestJpaDelete()
    {
        personBO.TestJpaDelete();
        return "Delete Ok";
    }
}
