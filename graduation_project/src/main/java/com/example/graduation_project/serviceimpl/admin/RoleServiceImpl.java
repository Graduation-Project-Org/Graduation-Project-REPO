package com.example.graduation_project.serviceimpl.admin;

import com.example.graduation_project.entities.admin.RoleEntity;
import com.example.graduation_project.repositories.admin.RoleRepository;
import com.example.graduation_project.services.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;


    @Override
    public List<RoleEntity> getListRole() {
        return roleRepository.findAll();
    }

    @Override
    public boolean findByCode(String code) {
        if (roleRepository.findByCode(code)!=null){
            return false;
        }
        return true;
    }

    @Override
    public RoleEntity save(RoleEntity entity) {
        return roleRepository.save(entity);
    }

    @Override
    public RoleEntity getRoleById(Long id) {
        Optional<RoleEntity> optional = roleRepository.findById(id);
        RoleEntity role = null;
        if (optional.isPresent()) {
            role = optional.get();
        } else {
            throw new RuntimeException(" Test exam not found for id :: " + id);
        }
        return role;
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
