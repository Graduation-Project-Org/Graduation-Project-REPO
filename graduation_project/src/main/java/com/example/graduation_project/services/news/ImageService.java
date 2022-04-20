package com.example.graduation_project.services.news;

import com.example.graduation_project.entities.news.ImageOfNewsEntity;

import java.util.List;

public interface ImageService {
    List< ImageOfNewsEntity > findAll();
    List<ImageOfNewsEntity> findAllByNewsEntity_NewsId(Long id);
}
