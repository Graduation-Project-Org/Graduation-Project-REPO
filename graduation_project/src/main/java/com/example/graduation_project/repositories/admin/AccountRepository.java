package com.example.graduation_project.repositories.admin;


import com.example.graduation_project.entities.admin.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {

    AccountEntity findByUserName(String userName);


}
