package com.example.graduation_project.repositories.admin;

import com.example.graduation_project.entities.admin.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<RoleEntity,Long> {

    RoleEntity findByCode(String code);
}
