package com.pweb.dulich.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourIndex {
    private Long id;
    private String tourName;
    private String schedule;
    private String length;
    private BigDecimal price;
    private String description;
    private String detailSchedule;
    private String note;
    private String listStartDate;
    private String image1;
    private String image2;
    private String image3;
    private String image4;


    
}
