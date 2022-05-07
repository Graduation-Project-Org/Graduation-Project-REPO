package com.example.graduation_project.dto;

import com.example.graduation_project.entities.customer.CustomerEntity;
import com.example.graduation_project.entities.news.CategoryEntity;
import com.example.graduation_project.entities.news.DirectionEntity;
import com.example.graduation_project.entities.news.ImageOfNewsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {
    private Long newsId;

    private String title;

    private String address;

    private String description;

    private Long price;

    private Long area;

    private Date dateOfNews;

    private int status;

    private Long categoryId;

    private Long directionId;
    private MultipartFile[] images;
    private List<ImageOfNewsEntity> imageList;
}
