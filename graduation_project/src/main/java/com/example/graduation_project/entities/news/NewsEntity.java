package com.example.graduation_project.entities.news;

import com.example.graduation_project.entities.customer.CustomerEntity;
import com.example.graduation_project.entities.employee.EmployeeEntity;
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

    private Long price;

    private Long area;

    @Column(name = "date_of_news")
    private Date dateOfNews;

    private int status;

    @ManyToOne(targetEntity = CustomerEntity.class)
    private CustomerEntity customerEntity;

    @ManyToOne(targetEntity = CategoryEntity.class)
    private CategoryEntity categoryEntity;

    @ManyToOne(targetEntity = DirectionEntity.class)
    private DirectionEntity directionEntity;

    @OneToMany(mappedBy = "newsEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(name = "images")
    private List<ImageOfNewsEntity > imageOfNewsEntityList;
}
