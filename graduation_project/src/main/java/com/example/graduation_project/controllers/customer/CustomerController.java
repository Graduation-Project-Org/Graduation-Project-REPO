package com.example.graduation_project.controllers.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

    @GetMapping(value = {"","/"})
    public  String homePage(ModelMap model)
    {
        return "/customer/home";
    }
}
