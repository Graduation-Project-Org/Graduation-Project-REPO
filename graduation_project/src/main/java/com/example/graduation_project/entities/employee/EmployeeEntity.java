package com.example.graduation_project.entities.employee;

import com.example.graduation_project.entities.news.NewsEntity;
import com.example.graduation_project.entities.admin.AccountEntity;
import com.example.graduation_project.entities.admin.EmployeeIsdueEnitity;
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
@Table(name = "employee")
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="employee_id")
    private  Long employeeId;

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

    private  double salary;

    private  String atm;

    @Column(name ="personal_income_tax_code",length = 50)
    private  String personalIncomeTaxCode;

    @ManyToOne
    @JoinColumn(name = "id")
    private AccountEntity accounts;


    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "employeeEntity",
            fetch = FetchType.LAZY)

    private List< EmployeeIsdueEnitity > employeeIsdueEnitityList;



    @ManyToOne
    @JoinColumn(name = "positionId")
    private PositionEntity positionEntities;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private DepartmentEntity departmentEntity;


    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "employeeEntity",
            fetch = FetchType.LAZY)

    private  List< NewsEntity > newsEntityList;
}
