package com.example.graduation_project.controllers.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @GetMapping(value = {"","/"})
    public String homePage(ModelMap model){
        return "/manager/home";
    }
}
