package com.example.graduation_project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name ="customter_id")
    private  Long customerId;

    @Column(name ="first_name",length = 30)
    private  String firstName;

    @Column(name ="last_name",length = 30)
    private  String lastName;

    private Date dob;

    private  String address;

    private  int gender;

    private  String sdt;

    @Column(name ="card_id",length = 13)
    private  String cardId;

    private  String image;

    @ManyToOne
    @JoinColumn(name = "id")
    private AccountEntity accounts;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "accounts")
    private List<EmployeeEntity> employeeEntityList;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customerEntity")
    private List<NewsEntity> newsEntityList;





}
