package com.example.graduation_project.controllers.admin;

import com.example.graduation_project.dto.RoleDto;
import com.example.graduation_project.entities.admin.RoleEntity;
import com.example.graduation_project.services.admin.RoleService;
import com.example.graduation_project.util.Status;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("")
    public String homePage(ModelMap model) {
        model.addAttribute("listRole", roleService.getListRole());
        return "/admin/role/homeRole";
    }

    @GetMapping("/add")
    public String saveOrEditPage(ModelMap model) {

        model.addAttribute("roledto", new RoleDto());

        return "/admin/role/saveOrEdit";

    }


    @PostMapping("/saveOrEdit")
    public String saveOrUpdate(ModelMap model,
                               @Valid @ModelAttribute("roledto") RoleDto dto,
                               BindingResult result) {
        try {
            if(result.hasErrors()) {
                return "/admin/role/saveOrEdit";
            }
           if(!roleService.findByCode(dto.getCode()) && dto.getIsEdit()){
                model.addAttribute("listRole", roleService.getListRole());
                model.addAttribute("error", Status.existCode);
                return "/admin/role/saveOrEdit";
            }else{
                RoleEntity entity= new RoleEntity();
                BeanUtils.copyProperties(dto, entity);
                roleService.save(entity);

                model.addAttribute("listRole", roleService.getListRole());

                if(!dto.getIsEdit()) {
                    model.addAttribute("message", Status.saveSuccess);
                }else {
                    model.addAttribute("message", Status.updateSuccess);
                }

            }
            return "/admin/role/homeRole";

        } catch (Exception e) {
            // TODO: handle exception
            if(!dto.getIsEdit()) {
                model.addAttribute("error",Status.saveFail);
            }else {
                model.addAttribute("error", Status.updateFail);
            }
            return "/admin/role/homeRole";
        }
    }

    @GetMapping("/edit-id={roleId}")
    public String edit(ModelMap model, @PathVariable("roleId") Long id) {
        RoleDto dto = new RoleDto();
        dto.setIsEdit(true); // set update = true
        RoleEntity entity = roleService.getRoleById(id);
        BeanUtils.copyProperties(entity, dto);
        model.addAttribute("roledto", dto);
        return "/admin/role/saveOrEdit";
    }

    @GetMapping("/delete-id={roleId}")
    public String delete(ModelMap model, @PathVariable("roleId") Long id) {
        try {
            roleService.deleteById(id);
            model.addAttribute("listRole", roleService.getListRole());
            model.addAttribute("message", Status.deleteSuccess);
            return "/admin/role/homeRole";
        } catch (Exception e) {
            model.addAttribute("listRole", roleService.getListRole());
            model.addAttribute("error", id+ Status.deleteFail);
            return "/admin/role/homeRole";
        }


    }




}
