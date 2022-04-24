package com.example.graduation_project.controllers.customer;

import com.example.graduation_project.dto.CustomerDto;
import com.example.graduation_project.entities.customer.CustomerEntity;
import com.example.graduation_project.serviceimpl.customer.CustomerServiceImpl;
import com.example.graduation_project.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("")
    public String showListAndSearch(@RequestParam(defaultValue = "") String name,
                                    @RequestParam(defaultValue = "") String phone,
                                    @RequestParam(defaultValue = "") String address,
                                    @RequestParam(defaultValue = "0") int page,
                                    Model model) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("customer_id"));
        Page< CustomerEntity > customerEntityPage = customerService.findAllByNameAndPhoneAndAddress(
                name, phone, address, pageable);
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        model.addAttribute("address", address);
//        model.addAttribute("page", page);
        if (customerEntityPage.isEmpty()) {
            model.addAttribute("message", "Danh sách không tìm thấy");
            return "error404";
        } else {
            model.addAttribute("customerEntityPage", customerEntityPage);
        }
        return "/customer/list";
    }

    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        Optional< CustomerEntity > customerEntity = customerService.findById(id);
        if (customerEntity.isPresent()) {
            model.addAttribute("customerEntity", customerEntity.get());
            return "customer/detail";
        } else {
            model.addAttribute("message", "Không tìm thấy thông tin khách hàng");
            return "error404";
        }
    }

    @GetMapping("/delete/{id}")
    public String showDetailDelete(@PathVariable Long id, Model model) {
        Optional< CustomerEntity > customerEntity = customerService.findById(id);
        if (customerEntity.isPresent()) {
            model.addAttribute("customerEntity", customerEntity.get());
            return "customer/delete";
        } else {
            model.addAttribute("message", "Không tìm thấy thông tin khách hàng");
            return "error404";
        }

    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id,
                         Model model) {
        customerService.delete(id);
        return "redirect:customer/list";
    }

    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Optional< CustomerEntity > customerEntity = customerService.findById(id);
        if (customerEntity.isPresent()) {
            model.addAttribute("customerEntity", customerEntity);
            return "customer/edit";
        } else {
            model.addAttribute("message", "Không tìm thấy thông tin khách hàng");
            return "error404";
        }
    }

//    @GetMapping("/create")
//    public String showFormCreate(@ModelAttribute CustomerDto customerDto, Model model) {
//        model.addAttribute("customerDto", customerDto);
//        return "customer/create";
//    }
//    @GetMapping("/create")
//    public String createCustomer(@ModelAttribute CustomerDto customerDto, Model model) {
//        model.addAttribute("customerDto", customerDto);
//        return "customer/create";
//    }
}

