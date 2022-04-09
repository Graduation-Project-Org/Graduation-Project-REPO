package com.example.graduation_project.controllers.employee;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping(value = {"","/"})
    public String homePage(ModelMap model){
        return "/employee/home";
    }
}
