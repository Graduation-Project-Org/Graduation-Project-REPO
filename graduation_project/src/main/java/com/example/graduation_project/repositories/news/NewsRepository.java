package com.example.graduation_project.repositories.news;

import com.example.graduation_project.entities.news.NewsEntity;
import org.hibernate.persister.entity.Loadable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository< NewsEntity, Long > {
    // show list
    @Query(value = " select * \n" +
            " from `news` \n " +
            " where `address` like concat('%',trim(:address),'%')\n " +
            " and `category_entity_category_id` like concat('%',trim(:category),'%')\n " +
            " and `direction_entity_direction`_id like concat('%',trim(:direction),'%')\n " +
            " and `title` like concat('%',trim(:title),'%')\n " +
            " and `area` between :minArea and :maxArea " +
            " and `price` between :minPrice and :maxPrice " +
            " and `status` =1", nativeQuery = true,
            countQuery = " select count(*) from `news` " +
                    " where `address` like concat('%',trim(:address),'%')\n " +
                    " and `category_entity_category_id` like concat('%',trim(:category),'%')\n " +
                    " and `direction_entity_direction_id` like concat('%',trim(:direction),'%')\n " +
                    " and `title` like concat('%',trim(:title),'%')\n " +
                    " and `area` between :minArea and :maxArea " +
                    " and `price` between :minPrice and :maxPrice " +
                    " and `status` = 1")
    Page< NewsEntity > findAllNewsByFilter(@Param("address") String address,
                                           @Param("category") String category,
                                           @Param("direction") String direction,
                                           @Param("title") String title,
                                           @Param("minArea") String minArea,
                                           @Param("maxArea") String maxArea,
                                           @Param("minPrice") String minPrice,
                                           @Param("maxPrice") String maxPrice,
                                           Pageable pageable);

    // show customer history list
    @Query(value = " select * from `news` " +
            " where `customer_entity_customer_id` = :customerId  " +
            " and `category_entity_category_id` like concat('%',:category,'%') " +
            " and `direction_entity_direction_id` like concat('%',:direction,'%') " +
            " and `title` like concat('%',trim(:title),'%') " +
            " and `status` like concat('%',:status,'%')", nativeQuery = true)
//            countQuery = " select count(*) from news " +
//                    " where customer_entity_customer_id =:customerId " +
//                    " and category_entity_category_id like concat('%',:category,'%') " +
//                    " and direction_entity_direction_id like concat('%',:direction,'%')  " +
//                    " and title like concat('%',trim(:title),'%') " +
//                    " and status = like concat('%',:status,'%')")
    Page< NewsEntity > findAllHistoryListNewOfCustomer(@Param("customerId") String customerId,
                                                       @Param("category") String category,
                                                       @Param("direction") String direction,
                                                       @Param("title") String title,
                                                       @Param("status") String status,
                                                       Pageable pageable);

    // show status news list
    @Query(value = " select * from `news` " +
            " and `title` like concat('%',trim(:title),'%')\n " +
            " and `status` like concat('%',trim(:status),'%')", nativeQuery = true,
            countQuery = " select count(*) from news " +
                    " where `address` like concat('%',trim(:address),'%')\n " +
                    " and `category_entity_category_id` like concat('%',trim(:category),'%')\n " +
                    " and `direction_entity_direction_id` like concat('%',trim(:direction),'%')\n " +
                    " and `title` like concat('%',trim(:title),'%')\n " +
                    " and `status` = like concat('%',trim(:status),'%')")
    Page< NewsEntity > findAllListNewByStatus(@Param("title") String title,
                                              @Param("status") String status,
                                              Pageable pageable);


}
