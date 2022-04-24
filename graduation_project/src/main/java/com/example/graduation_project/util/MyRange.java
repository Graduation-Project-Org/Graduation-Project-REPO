package com.example.graduation_project.util;

import com.example.graduation_project.entities.news.AreaRange;
import com.example.graduation_project.entities.news.PriceRange;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

public class MyRange {
    public static List< AreaRange> getAreaRange() {
        List< AreaRange > areaRanges = new ArrayList<>();
        areaRanges.add(new AreaRange(1L,0.0,50.0,"0-50 m²"));
        areaRanges.add(new AreaRange(2L,50.0,100.0,"50-100 m²"));
        areaRanges.add(new AreaRange(3L,100.0,200.0,"100-200 m²"));
        areaRanges.add(new AreaRange(4L,200.0,200.0,"200-500 m²"));
        areaRanges.add(new AreaRange(5L,200.0,100000000.0,"500+ m²"));
        return areaRanges;
    }
    public static List<PriceRange> getPriceRange(){
        List< PriceRange > priceRanges = new ArrayList<>();
        priceRanges.add(new PriceRange(1L,0.0,100000000.0,"0-100 triệu" ));
        priceRanges.add(new PriceRange(2L,100000000.0,500000000.0,"100-500 triệu"));
        priceRanges.add(new PriceRange(3L,500000000.0,1000000000.0,"500 triệu - 1 tỷ" ));
        priceRanges.add(new PriceRange(4L,1000000000.0,5000000000.0,"1-5 tỷ" ));
        priceRanges.add(new PriceRange(5L,5000000000.0,1000000000.0,"5 - 10 tỷ" ));
        priceRanges.add(new PriceRange(6L,1000000000.0,200000000.0,"10 - 20 tỷ" ));
        priceRanges.add(new PriceRange(7L,200000000.0,10000000000000000.0,"20 tỷ +" ));

        return priceRanges;
    }
}
