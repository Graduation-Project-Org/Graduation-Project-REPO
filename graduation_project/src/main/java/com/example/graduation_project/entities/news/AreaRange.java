package com.example.graduation_project.entities.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaRange {
    private Long id;
    private Double minArea;
    private Double maxArea;
    private String name;
}
