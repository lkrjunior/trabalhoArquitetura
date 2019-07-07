package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class StatusController {

    @GetMapping("/status")
    @ResponseBody
    public String GetStatus()
    {
        return "1.0.0";
    }
}
