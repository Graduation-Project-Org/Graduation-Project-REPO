package com.example.graduation_project.serviceimpl.news;

import com.example.graduation_project.dto.CategoryDto;
import com.example.graduation_project.dto.NewsDto;
import com.example.graduation_project.entities.customer.CustomerEntity;
import com.example.graduation_project.entities.news.*;
import com.example.graduation_project.repositories.news.NewsRepository;
import com.example.graduation_project.services.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public Page< NewsEntity > findAllNewsByFilter(String address, String category,
                                                  String direction, String title,
                                                  String minArea, String maxArea,
                                                  String minPrice, String maxPrice,
                                                  Pageable pageable) {
        return newsRepository.findAllNewsByFilter(
                address, category, direction, title, minArea, maxArea, minPrice, maxPrice, pageable);
    }

    @Override
    public Page< NewsEntity > findAllHistoryListNewOfCustomer(String customerId, String category,
                                                              String direction, String title,
                                                              String status, Pageable pageable) {
        return newsRepository.findAllHistoryListNewOfCustomer(
                customerId, category, direction, title, status, pageable
        );
    }

    @Override
    public Page< NewsEntity > findAllListNewByStatus(String title, String status, Pageable pageable) {
        return newsRepository.findAllListNewByStatus(title, status, pageable);
    }

    @Override
    public Optional< NewsEntity > findById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public void sendSimpleMessage(String name, String customerEmail, String email, String comment) throws MessagingException, UnsupportedEncodingException {
        String mailContent = "";
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setFrom("plthienbkdn@gmail.com", "Bất động sản ABC");
        helper.setTo(customerEmail);
        helper.setSubject("Một khách hàng quan tâm tới bài đăng của bạn");
        mailContent = "<p> Chào bạn!</p>\n" +
                "<p>Khách hàng " + name + " " + email + " đã gửi cho bạn 1 thông báo với nội dung: </p>\n" +
                "<p>" + comment + "</p>\n" +
                "<p>Thanks and Regards</p>\n" +
                "<hr>";
        helper.setText(mailContent, true);
        emailSender.send(message);
    }

//    @Override
//    public Optional< NewsEntity > findLastNews() {
//        return newsRepository.findLastNews();
//    }

    @Override
    public NewsEntity save(NewsEntity newsEntity) {
        return newsRepository.save(newsEntity);
    }

    @Override
    public NewsEntity saveFromDTO(NewsDto dto, Long customerEntityId) {
        NewsEntity newsEntity = new NewsEntity();

        DirectionEntity directionEntity = new DirectionEntity();
        directionEntity.setDirectionId(dto.getDirectionId());

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(dto.getCategoryId());

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customerEntityId);

        newsEntity.setAddress(dto.getAddress());
        newsEntity.setTitle(dto.getTitle());
        newsEntity.setArea(dto.getArea());
        newsEntity.setPrice(dto.getPrice());
        newsEntity.setDescription(dto.getDescription());
        //Chuyen kieu Local date sang date + set date
        LocalDate localDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);
        newsEntity.setDateOfNews(date);
        //set status
        newsEntity.setStatus(0);
        //set customerEntity
        newsEntity.setCustomerEntity(customerEntity);
        //set CategoryEntity
        newsEntity.setCategoryEntity(categoryEntity);
        //set DirectionEntity
        newsEntity.setDirectionEntity(directionEntity);
        return save(newsEntity);
    }

    @Override
    public NewsDto convertNewsEntityToDto(NewsEntity newsEntity) {
        NewsDto newsDto = new NewsDto();
        newsDto.setAddress(newsEntity.getAddress());
        newsDto.setArea(newsEntity.getArea());
        newsDto.setCategoryId(newsEntity.getCategoryEntity().getCategoryId());
        newsDto.setDirectionId(newsEntity.getDirectionEntity().getDirectionId());
        newsDto.setPrice(newsEntity.getPrice());
        newsDto.setDescription(newsEntity.getDescription());
        newsDto.setTitle(newsEntity.getTitle());
        newsDto.setNewsId(newsEntity.getNewsId());
        newsDto.setDateOfNews(newsEntity.getDateOfNews());
        newsDto.setImageList(newsEntity.getImageOfNewsEntityList());
        newsDto.setStatus(newsEntity.getStatus());

        return newsDto;
    }


//    static {
//        List< PriceRange > priceRanges = new ArrayList<>();
//        priceRanges.add(new PriceRange(1L, 0.0, 100000000.0, "0-100 triệu"));
//        priceRanges.add(new PriceRange(2L, 100000000.0, 500000000.0, "100-500 triệu"));
//        priceRanges.add(new PriceRange(3L, 500000000.0, 1000000000.0, "500 triệu - 1 tỷ"));
//        priceRanges.add(new PriceRange(4L, 1000000000.0, 5000000000.0, "1-5 tỷ"));
//        priceRanges.add(new PriceRange(5L, 5000000000.0, 1000000000.0, "5 - 10 tỷ"));
//        priceRanges.add(new PriceRange(6L, 1000000000.0, 200000000.0, "10 - 20 tỷ"));
//        priceRanges.add(new PriceRange(7L, 200000000.0, 10000000000000000.0, "20 tỷ +"));
//
//        List< AreaRange > areaRanges = new ArrayList<>();
//        areaRanges.add(new AreaRange(1L, 0.0, 50.0, "0-50 m²"));
//        areaRanges.add(new AreaRange(2L, 50.0, 100.0, "50-100 m²"));
//        areaRanges.add(new AreaRange(3L, 100.0, 200.0, "100-200 m²"));
//        areaRanges.add(new AreaRange(4L, 200.0, 200.0, "200-500 m²"));
//        areaRanges.add(new AreaRange(5L, 200.0, 100000000.0, "500+ m²"));
//    }
}
