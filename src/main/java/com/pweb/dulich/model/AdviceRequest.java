package com.pweb.dulich.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdviceRequest {

    //bao gồm các trường id, tour_id là khoá ngoại, request_time, status, customer_name, phone_number, note
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_time", columnDefinition = "DATETIME")
    private Date requestTime;

    @Column(name = "status")
    private Integer status;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "note", columnDefinition = "LONGTEXT")
    private String note;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;
    
}
