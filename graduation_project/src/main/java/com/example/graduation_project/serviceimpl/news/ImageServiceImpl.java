package com.example.graduation_project.serviceimpl.news;

import com.example.graduation_project.entities.news.ImageOfNewsEntity;
import com.example.graduation_project.repositories.news.ImageOfNewsRepository;
import com.example.graduation_project.services.news.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageOfNewsRepository imageOfNewsRepository;
    @Override
    public List< ImageOfNewsEntity > findAll() {
        return imageOfNewsRepository.findAll();
    }

    @Override
    public List< ImageOfNewsEntity > findAllByNewsEntity_NewsId(Long id) {
        return imageOfNewsRepository.findAllByNewsEntity_NewsId(id);
    }
}
