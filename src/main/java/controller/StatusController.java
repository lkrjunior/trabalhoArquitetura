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
    public String getStatus()
    {
        Version version = versionBO.getVersion();
        return version.getValue();
    }

    @GetMapping("")
    @ResponseBody
    public ModelAndView home()
    {
        return new ModelAndView("home");
    }
}
