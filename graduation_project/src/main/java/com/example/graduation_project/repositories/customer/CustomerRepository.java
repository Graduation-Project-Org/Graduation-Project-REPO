package com.example.graduation_project.repositories.customer;

import com.example.graduation_project.entities.customer.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository< CustomerEntity, Long > {
    @Query(value = " select * from customer c " +
            " where c.first_name like concat('%',trim(:name),'%') " +
            " and c.phone_number like concat('%',trim(:phone),'%') " +
            " and c.address like concat('%',trim(:address),'%')", nativeQuery = true,
            countQuery = " select count(*) from customer c" +
                    " where c.first_name like concat('%',trim(:name),'%')" +
                    " and c.phone_number like concat('%',trim(:phone),'%')" +
                    " and c.address like concat('%',trim(:address),'%')")
    Page< CustomerEntity > FindAllCustomerByNamePhoneAddress
    (@Param("name") String name,
     @Param("phone") String phone,
     @Param("address") String email,
     @Param("page") Pageable pageable);
}
