package com.example.graduation_project.entities.customer;

import com.example.graduation_project.entities.employee.EmployeeEntity;
import com.example.graduation_project.entities.news.NewsEntity;
import com.example.graduation_project.entities.admin.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="customer_id")
    private  Long customerId;

    @Column(name ="first_name",length = 30)
    private  String firstName;

    @Column(name ="last_name",length = 30)
    private  String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private  String address;

    private  int gender;

    private  String phoneNumber;

    @Column(name ="card_id",length = 13)
    private  String idCard;

    private  String image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private AccountEntity account;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customerEntity")
    private List< NewsEntity > newsEntityList;





}
