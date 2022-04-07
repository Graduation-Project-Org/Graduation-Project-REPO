package com.example.graduation_project.repositories;

import com.example.graduation_project.entities.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity,Long> {

    AccountEntity findByUserName(String userName);


}
