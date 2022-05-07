package com.example.graduation_project.controllers.news;

import com.example.graduation_project.dto.NewsDto;
import com.example.graduation_project.entities.customer.CustomerEntity;
import com.example.graduation_project.entities.news.*;
import com.example.graduation_project.services.news.CategoryService;
import com.example.graduation_project.services.news.DirectionService;
import com.example.graduation_project.services.news.ImageService;
import com.example.graduation_project.services.news.NewsService;
import com.example.graduation_project.util.MyRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RequestMapping("news")
@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private DirectionService directionService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("Image")
    public Iterable< ImageOfNewsEntity > imageList(Model model) {
        return imageService.findAll();
    }

    @ModelAttribute("categoryEntityList")
    public Iterable< CategoryEntity > categoryEntityList(Model model) {
        return categoryService.findALl();
    }

    @ModelAttribute("directionEntityList")
    public Iterable< DirectionEntity > directionEntityList(Model model) {
        return directionService.findAll();
    }

//    @ModelAttribute("areaRanges")
//    public Iterable< AreaRange > areaRanges(Model model) {
//        return MyRange.getAreaRange();
//    }
//
//    @ModelAttribute("priceRanges")
//    public Iterable< PriceRange > priceRanges(Model model) {
//        return MyRange.getPriceRange();
//    }

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

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
        Pageable pageable = PageRequest.of(page, 9, Sort.by("date_of_news").descending());
        Page< NewsEntity > newsEntityPage = newsService.
                findAllNewsByFilter(address, category, direction, title, minArea, maxArea, minPrice, maxPrice, pageable);
        if (newsEntityPage.isEmpty()) {
            model.addAttribute("message", "Danh sách không tìm thấy");
            return "real_estate_new/list";
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
            return "real_estate_new/list";
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
            return "real_estate_new/list";
        }
        Optional< NewsEntity > newsEntity = newsService.findById(id);
        if (!newsEntity.isPresent()) {
            model.addAttribute("message", "Thông tin không tồn tại");
            return "real_estate_new/list";
        }
//        List< ImageOfNewsEntity > images = imageService.findAllByNewsEntity_NewsId(id);
        model.addAttribute("newsEntity", newsEntity.get());
//        model.addAttribute("images", images);
        return "real_estate_new/detail";
    }

    @GetMapping("detail/email")
    public String emailSend(@RequestParam("email") String email,
                            @RequestParam("customerEmail") String customerEmail,
                            @RequestParam("name") String name,
                            @RequestParam("comment") String comment,
                            @RequestParam("id") Long id,
                            Model model
    ) throws UnsupportedEncodingException, MessagingException {
        newsService.sendSimpleMessage(name, customerEmail, email, comment);
        Optional< NewsEntity > newsEntity = newsService.findById(id);
        model.addAttribute("newsEntity", newsEntity.get());
        model.addAttribute("message", "Đã gửi mail thành công");
        return "real_estate_new/detail";
    }

    @GetMapping("/creates")
    public String showFormCreate(Model model) {
        model.addAttribute("newsDto", new NewsDto());
        return "real_estate_new/create";
    }

    @PostMapping("/create")
    public String showFormCreate(NewsDto newsDto,
                                 Model model)
            throws IOException {
        Long customerEntityId = 1L;
        NewsEntity newsEntity = newsService.saveFromDTO(newsDto, customerEntityId);
        model.addAttribute("newsDto", new NewsDto());
        Path staticPath = Paths.get("resources/static");
        Path imagePath = Paths.get("image");
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customerEntityId);
        MultipartFile[] images = newsDto.getImages();
        for (MultipartFile image : images) {
            if (!Files.exists(imagePath)) {
                Files.createDirectories(imagePath);
            }
            UUID uuid = UUID.randomUUID();
            String imageName = "image/" +uuid.toString() + StringUtils.cleanPath(image.getOriginalFilename());

            Path file = CURRENT_FOLDER.resolve(staticPath)
                    .resolve(imagePath).resolve(image.getOriginalFilename());
            // change name image => no duplicate image

            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(image.getBytes());
                ImageOfNewsEntity imageOfNewsEntity = new ImageOfNewsEntity();
                imageOfNewsEntity.setName(imageName);
                imageOfNewsEntity.setNewsEntity(newsEntity);
                imageService.save(imageOfNewsEntity);
            }

        }
        return "real_estate_new/create";
    }
    @GetMapping("/edits/{id}")
    public String showNewsFormEdit(@PathVariable Long id,
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
        NewsDto newsDto = newsService.convertNewsEntityToDto(newsEntity.get());
        model.addAttribute("newsDto", newsDto);

        return "real_estate_new/edit";
    }

}
