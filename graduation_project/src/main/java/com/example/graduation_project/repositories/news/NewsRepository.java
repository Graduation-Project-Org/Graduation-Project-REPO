package com.example.graduation_project.repositories.news;

import com.example.graduation_project.entities.news.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository< NewsEntity, Long > {
}
