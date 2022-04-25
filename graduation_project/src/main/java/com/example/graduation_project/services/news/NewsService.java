package com.example.graduation_project.services.news;

import com.example.graduation_project.entities.news.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface NewsService {
    Page< NewsEntity > findAllNewsByFilter(@Param("address") String address,
                                           @Param("category") String category,
                                           @Param("direction") String direction,
                                           @Param("title") String title,
                                           @Param("minArea") String minArea,
                                           @Param("maxArea") String maxArea,
                                           @Param("minPrice") String minPrice,
                                           @Param("maxPrice") String maxPrice,
                                           Pageable pageable);

    Page< NewsEntity > findAllHistoryListNewOfCustomer(@Param("customerId") String customerId,
                                                       @Param("category") String category,
                                                       @Param("direction") String direction,
                                                       @Param("title") String title,
                                                       @Param("status") String status,
                                                       Pageable pageable);

    Page< NewsEntity > findAllListNewByStatus(@Param("title") String title,
                                              @Param("status") String status,
                                              Pageable pageable);


    Optional< NewsEntity > findById(Long id);

    void sendSimpleMessage(String name,String customerEmail, String email, String comment) throws MessagingException, UnsupportedEncodingException;


}
