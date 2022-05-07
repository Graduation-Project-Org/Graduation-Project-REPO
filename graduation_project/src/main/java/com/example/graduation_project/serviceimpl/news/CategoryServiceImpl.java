package com.example.graduation_project.serviceimpl.news;

import com.example.graduation_project.entities.news.CategoryEntity;
import com.example.graduation_project.repositories.news.CategoryRepository;
import com.example.graduation_project.services.news.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List< CategoryEntity > findALl() {
        return categoryRepository.findAll();
    }
}
