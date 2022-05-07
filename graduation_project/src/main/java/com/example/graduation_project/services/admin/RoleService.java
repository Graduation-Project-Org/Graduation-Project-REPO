package com.example.graduation_project.services.admin;


import com.example.graduation_project.entities.admin.RoleEntity;

import java.util.List;

public interface RoleService {

    List<RoleEntity> getListRole();

    boolean findByCode(String code);

    RoleEntity save(RoleEntity entity);

    RoleEntity getRoleById(Long id);

    void deleteById(Long id);
}
