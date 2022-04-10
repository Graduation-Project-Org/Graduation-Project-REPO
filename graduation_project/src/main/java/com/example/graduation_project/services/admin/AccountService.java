package com.example.graduation_project.services.admin;

import com.example.graduation_project.entities.admin.AccountEntity;

public interface AccountService {

    AccountEntity findByUsername(String userName);

}
