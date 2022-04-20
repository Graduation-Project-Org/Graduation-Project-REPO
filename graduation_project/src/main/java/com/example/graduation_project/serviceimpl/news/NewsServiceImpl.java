package com.example.graduation_project.serviceimpl.news;

import com.example.graduation_project.entities.news.NewsEntity;
import com.example.graduation_project.repositories.news.NewsRepository;
import com.example.graduation_project.services.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Override
    public Page< NewsEntity > findAllNewsByFilter(String address, String category,
                                                  String direction, String title,
                                                  String minArea, String maxArea,
                                                  String minPrice, String maxPrice,
                                                  Pageable pageable) {
        return newsRepository.findAllNewsByFilter(
                address, category, direction, title, minArea,maxArea,minPrice,maxPrice,pageable);
    }

    @Override
    public Optional< NewsEntity > findById(Long id) {
        return newsRepository.findById(id);
    }
}
