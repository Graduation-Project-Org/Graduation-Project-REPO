package com.example.graduation_project.services;

import com.example.graduation_project.entities.AccountEntity;

public interface AccountService {

    AccountEntity findByUsername(String userName);

}
