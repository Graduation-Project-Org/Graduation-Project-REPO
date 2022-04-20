package com.example.graduation_project.entities.admin;

import com.example.graduation_project.entities.admin.IssueEntity;
import com.example.graduation_project.entities.employee.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issue_employee")
public class EmployeeIssueEnitity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long creatorId;
    @ManyToOne
    @JoinColumn(name = "issueId")
    private IssueEntity issueEntity;


    @ManyToOne
    @JoinColumn(name = "employeeId")
    private EmployeeEntity employeeEntity;


}
