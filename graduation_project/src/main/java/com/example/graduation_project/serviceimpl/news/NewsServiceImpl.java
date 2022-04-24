package com.example.graduation_project.serviceimpl.news;

import com.example.graduation_project.entities.news.AreaRange;
import com.example.graduation_project.entities.news.NewsEntity;
import com.example.graduation_project.entities.news.PriceRange;
import com.example.graduation_project.repositories.news.NewsRepository;
import com.example.graduation_project.services.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public Page< NewsEntity > findAllHistoryListNewOfCustomer(String customerId, String category,
                                                              String direction, String title,
                                                              String status, Pageable pageable) {
        return newsRepository.findAllHistoryListNewOfCustomer(
                customerId,category,direction,title,status,pageable
        );
    }

    @Override
    public Page< NewsEntity > findAllListNewByStatus(String title, String status, Pageable pageable) {
        return newsRepository.findAllListNewByStatus(title,status,pageable);
    }

    @Override
    public Optional< NewsEntity > findById(Long id) {
        return newsRepository.findById(id);
    }

    static {
        List< PriceRange > priceRanges = new ArrayList<>();
        priceRanges.add(new PriceRange(1L,0.0,100000000.0,"0-100 triệu" ));
        priceRanges.add(new PriceRange(2L,100000000.0,500000000.0,"100-500 triệu"));
        priceRanges.add(new PriceRange(3L,500000000.0,1000000000.0,"500 triệu - 1 tỷ" ));
        priceRanges.add(new PriceRange(4L,1000000000.0,5000000000.0,"1-5 tỷ" ));
        priceRanges.add(new PriceRange(5L,5000000000.0,1000000000.0,"5 - 10 tỷ" ));
        priceRanges.add(new PriceRange(6L,1000000000.0,200000000.0,"10 - 20 tỷ" ));
        priceRanges.add(new PriceRange(7L,200000000.0,10000000000000000.0,"20 tỷ +" ));

        List< AreaRange > areaRanges = new ArrayList<>();
        areaRanges.add(new AreaRange(1L,0.0,50.0,"0-50 m²"));
        areaRanges.add(new AreaRange(2L,50.0,100.0,"50-100 m²"));
        areaRanges.add(new AreaRange(3L,100.0,200.0,"100-200 m²"));
        areaRanges.add(new AreaRange(4L,200.0,200.0,"200-500 m²"));
        areaRanges.add(new AreaRange(5L,200.0,100000000.0,"500+ m²"));
    }
}
