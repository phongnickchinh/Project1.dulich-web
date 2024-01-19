package com.pweb.dulich.dto;

import java.util.Date;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {

    private Long id;
    private Date requestTime;
    private Integer status;
    private String customerName;
    private String phoneNumber;
    private String email;
    private String address;
    private Integer over11yr;
    private Integer from6to11yr;
    private Integer under6yr;
    private String note;
    private String startDate;
    private String tourId;
    
}
