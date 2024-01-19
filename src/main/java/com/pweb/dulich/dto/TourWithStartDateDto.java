package com.pweb.dulich.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourWithStartDateDto {
    private Long id;
    private String tourName;
    private String schedule;
    private String length;
    private BigDecimal price;
    private String description;
    private String detailSchedule;
    private String note;
    private String listStartDate;
    private MultipartFile image1;
    private MultipartFile image2;
    private MultipartFile image3;
    private MultipartFile image4;
}
