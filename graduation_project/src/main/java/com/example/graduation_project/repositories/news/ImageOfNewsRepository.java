package com.example.graduation_project.repositories.news;

import com.example.graduation_project.entities.news.ImageOfNewsEntity;
import com.example.graduation_project.entities.news.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageOfNewsRepository extends JpaRepository< ImageOfNewsEntity, Long > {
    List<ImageOfNewsEntity> findAllByNewsEntity_NewsId(Long id);

}
