package com.example.graduation_project.services.admin;

import com.example.graduation_project.entities.admin.AccountEntity;

import java.util.List;

public interface AccountService {

    AccountEntity findByUsername(String userName);
    List<AccountEntity> getListAccount();

}
