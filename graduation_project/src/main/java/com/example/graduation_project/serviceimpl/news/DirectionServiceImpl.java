package com.example.graduation_project.serviceimpl.news;

import com.example.graduation_project.entities.news.DirectionEntity;
import com.example.graduation_project.repositories.news.DirectionRepository;
import com.example.graduation_project.services.news.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DirectionServiceImpl implements DirectionService {
    @Autowired
    private DirectionRepository directionRepository;
    @Override
    public List< DirectionEntity > findAll() {
        return directionRepository.findAll();
    }
}
