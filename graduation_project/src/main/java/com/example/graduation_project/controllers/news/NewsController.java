package com.example.graduation_project.controllers.news;

import com.example.graduation_project.entities.news.*;
import com.example.graduation_project.services.news.DirectionService;
import com.example.graduation_project.services.news.ImageService;
import com.example.graduation_project.services.news.NewsService;
import com.example.graduation_project.util.MyRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("news")
@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private DirectionService directionService;
    @ModelAttribute("Image")
    public Iterable< ImageOfNewsEntity > imageList(Model model) {
        return imageService.findAll();
    }
    @ModelAttribute("directions")
    public Iterable< DirectionEntity > directionList(Model model) {
        return directionService.findAll();
    }
    @ModelAttribute("areaRanges")
    public Iterable< AreaRange > areaRanges(Model model) {
        return MyRange.getAreaRange();
    }
    @ModelAttribute("priceRanges")
    public Iterable< PriceRange > priceRanges(Model model) {
        return MyRange.getPriceRange();
    }
    // show list news
    @GetMapping("")
    public String getListNews(
            @RequestParam(defaultValue = "", value = "address") String address,
            @RequestParam(defaultValue = "", value = "category") String category,
            @RequestParam(defaultValue = "", value = "direction") String direction,
            @RequestParam(defaultValue = "", value = "title") String title,
            @RequestParam(defaultValue = "0", value = "minArea") String minArea,
            @RequestParam(defaultValue = "10000000000000000000000", value = "maxArea") String maxArea,
            @RequestParam(defaultValue = "0", value = "minPrice") String minPrice,
            @RequestParam(defaultValue = "10000000000000000000000", value = "maxPrice") String maxPrice,
            @RequestParam(defaultValue = "0") int page,
            Model model
    ) {
        Pageable pageable = PageRequest.of(page, 6, Sort.by("date_of_news").descending());
        Page< NewsEntity > newsEntityPage = newsService.
                findAllNewsByFilter(address, category, direction, title, minArea, maxArea, minPrice, maxPrice, pageable);
        if (newsEntityPage.isEmpty()) {
            model.addAttribute("message", "Danh sách không tìm thấy");
            return "error404";
        } else {
            model.addAttribute("newsEntityPage", newsEntityPage);
        }
        return "real_estate_new/list";
    }

    // show history list news of customer
    @GetMapping("/history_news/{customerId}")
    public String getHistoryListNews(
            @PathVariable(value = "customerId") String customerId,
            @RequestParam(defaultValue = "", value = "category") String category,
            @RequestParam(defaultValue = "", value = "direction") String direction,
            @RequestParam(defaultValue = "", value = "title") String title,
            @RequestParam(defaultValue = "", value = "status") String status,
            @RequestParam(defaultValue = "0") int page,
            Model model
    ) {
        Pageable pageable = PageRequest.of(page, 6, Sort.by("date_of_news").descending());
        Page< NewsEntity > newsEntityPage = newsService.
                findAllHistoryListNewOfCustomer(customerId, category, direction, title, status, pageable);
        if (newsEntityPage.isEmpty()) {
            model.addAttribute("message", "Danh sách không tìm thấy");
            return "error404";
        } else {
            model.addAttribute("newsEntityPage", newsEntityPage);
        }
        return "real_estate_new/historyListNews";
    }

    @GetMapping("/detail/{id}")
    public String showNewsDetail(@PathVariable Long id,
                                 Model model) {
        if (id == null) {
            model.addAttribute("message", "Thông tin không tồn tại");
            return "error404";
        }
        Optional< NewsEntity > newsEntity = newsService.findById(id);
        if (!newsEntity.isPresent()) {
            model.addAttribute("message", "Thông tin không tồn tại");
            return "error404";
        }
        List< ImageOfNewsEntity > images = imageService.findAllByNewsEntity_NewsId(id);
        model.addAttribute("newsEntity", newsEntity.get());
        model.addAttribute("images", images);
        return "real_estate_new/detail";

    }


}
