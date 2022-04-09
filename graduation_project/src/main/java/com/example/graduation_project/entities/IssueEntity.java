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
@Table(name = "issue")
public class IssueEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    @Column(columnDefinition="TEXT")
    private String content;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @OneToMany(mappedBy = "issueEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<EmployeeIsdueEnitity> employeeIsdueEnitityList;



}
