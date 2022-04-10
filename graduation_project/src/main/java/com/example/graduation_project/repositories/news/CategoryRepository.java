package com.example.graduation_project.repositories.news;

import com.example.graduation_project.entities.news.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository< CategoryEntity, Long > {
}
