package com.pweb.dulich.dto;

import java.util.Date;

import com.pweb.dulich.model.Tour;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdviceRequestDto {

    private Long id;
    private Date requestTime;
    private Integer status;
    private String customerName;
    private String phoneNumber;
    private String note;
    private String tourId;
    
}
