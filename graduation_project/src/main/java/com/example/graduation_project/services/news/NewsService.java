package com.example.graduation_project.services.news;

import com.example.graduation_project.entities.news.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NewsService {
    Page< NewsEntity > findAllNewsByFilter(@Param("address")String address,
                                           @Param("category")String category,
                                           @Param("direction")String direction,
                                           @Param("title")String title,
                                           @Param("minArea")String minArea,
                                           @Param("maxArea")String maxArea,
                                           @Param("minPrice")String minPrice,
                                           @Param("maxPrice")String maxPrice,
                                           Pageable pageable);
    Optional<NewsEntity> findById(Long id);
}
