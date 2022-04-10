package com.example.graduation_project.repositories.news;

import com.example.graduation_project.entities.news.ImageOfNewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageOfNewsRepository extends JpaRepository< ImageOfNewsEntity, Long > {
}
