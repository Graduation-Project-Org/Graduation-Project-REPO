package com.example.graduation_project.controllers.news;

import com.example.graduation_project.entities.news.ImageOfNewsEntity;
import com.example.graduation_project.entities.news.NewsEntity;
import com.example.graduation_project.services.news.ImageService;
import com.example.graduation_project.services.news.NewsService;
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

    @ModelAttribute("Image")
    public Iterable< ImageOfNewsEntity > imageList(Model model) {
        return imageService.findAll();
    }

    @GetMapping("")
    public String getListRealEstateNews(
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
        Pageable pageable = PageRequest.of(page, 8, Sort.by("date_of_news").descending());
        Page< NewsEntity > newsEntityPage = newsService.
                findAllNewsByFilter(address, category, direction, title, minArea, maxArea, minPrice, maxPrice, pageable);

       List<ImageOfNewsEntity> listImage = imageService.findAll();
        Map<Object, List<ImageOfNewsEntity>> map1 = listImage.stream().collect(Collectors.toMap
                (ImageOfNewsEntity::getNewsEntity, p-> {
            List<ImageOfNewsEntity> listUrl = new ArrayList<>();
            listUrl.add(p);
            return listUrl;

        }, (oldValue, newValue) -> {
            newValue.addAll(oldValue);
            return newValue;
        }));
        model.addAttribute("map1", map1);
        if (newsEntityPage.isEmpty()) {
            model.addAttribute("message", "Danh sách không tìm thấy");
            return "error404";
        } else {
            model.addAttribute("newsEntityPage", newsEntityPage);
        }
        return "real_estate_new/list";
    }

    @GetMapping("/detail/{id}")
    public String showNewsDetail(@PathVariable Long id,
                                 Model model) {
                if(id ==null){
            model.addAttribute("message", "Thông tin không tồn tại");
            return "error404";
        }
        Optional<NewsEntity> newsEntity = newsService.findById(id);
        if(!newsEntity.isPresent()){
            model.addAttribute("message", "Thông tin không tồn tại");
            return "error404";
        }
        List<ImageOfNewsEntity> images = imageService.findAllByNewsEntity_NewsId(id);
        model.addAttribute("newsEntity", newsEntity.get());
        model.addAttribute("images", images);
        return "real_estate_new/detail";

    }


}
