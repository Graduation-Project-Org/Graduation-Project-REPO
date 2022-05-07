package com.example.graduation_project.serviceimpl.admin;

import com.example.graduation_project.entities.admin.AccountEntity;
import com.example.graduation_project.repositories.admin.AccountRepository;
import com.example.graduation_project.services.admin.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl  implements AccountService {

    @Autowired
    AccountRepository repository;


    @Override
   public  List<AccountEntity> getListAccount(){
       return repository.findAll();
   }

    @Override
    public AccountEntity findByUsername(String userName){
        return findByUsername(userName);
    }


}
