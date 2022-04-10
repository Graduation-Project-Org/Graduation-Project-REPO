package com.example.graduation_project.repositories.news;


import com.example.graduation_project.entities.news.DirectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends JpaRepository< DirectionEntity, Long > {
}
