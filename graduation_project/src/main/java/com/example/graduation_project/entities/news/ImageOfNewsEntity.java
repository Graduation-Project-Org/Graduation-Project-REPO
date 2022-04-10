package com.example.graduation_project.entities.news;

import com.example.graduation_project.entities.news.NewsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="image")
public class ImageOfNewsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;

    @ManyToOne
    @JoinColumn(name = "newsId" )
    private NewsEntity newsEntity;


}
