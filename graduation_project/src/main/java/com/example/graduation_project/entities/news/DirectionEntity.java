package com.example.graduation_project.entities.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "direction")
public class DirectionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long directionId;

    private String name;

    @OneToMany (mappedBy = "directionEntity")
    private List< NewsEntity > newsEntity;
}
