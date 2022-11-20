package org.launchcode.mycodingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String displayMainPage() {
        return "index";
    }

}
