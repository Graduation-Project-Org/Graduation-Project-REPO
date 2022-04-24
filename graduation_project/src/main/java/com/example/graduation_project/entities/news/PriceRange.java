package com.example.graduation_project.entities.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRange {
    private Long id;
    private Double minPrice;
    private Double maxPrice;
    private String name;

}
