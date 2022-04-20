package com.example.graduation_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("test")
    public String login() {

        return "template";
    }
}
