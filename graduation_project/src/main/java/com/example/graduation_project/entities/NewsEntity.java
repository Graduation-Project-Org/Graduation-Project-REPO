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
@Table(name = "news")
public class NewsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long newsId;

    private String title;

    private String address;

    private String description;

    private  double area;

    @Column(name = "date_of_news")
    private Date dateOfNews;

    private  int agree;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private  EmployeeEntity employeeEntity;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private  CustomerEntity customerEntity;


    @OneToMany(mappedBy = "newsEntity",fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CategoryEntity> categoryEntityList;

     @OneToMany(mappedBy ="newsEntity" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<DirectionEntity>  directionEntityList;

     @OneToMany(mappedBy = "newsEntity",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private  List<ImageOfNewsEntity> imageOfNewsEntityList;
}
