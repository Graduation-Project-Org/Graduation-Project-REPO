package com.example.graduation_project.controllers.admin;

import com.example.graduation_project.entities.admin.AccountEntity;
import com.example.graduation_project.services.admin.AccountService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public  String homePage(ModelMap model){
       model.addAttribute("list", accountService.getListAccount());
        return "/admin/account/homeAccount";
    }

}
