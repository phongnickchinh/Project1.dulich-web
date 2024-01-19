package com.pweb.dulich.model;

import lombok. *;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    // các trường bao gồm: id, startDateTour là khoá ngoại, requestTime, status, customerName, phoneNumber, email, address, over11yr, 6to11yr, under6yr, note
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

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    // số người lớn hơn 11 tuổi
    @Column(name = "over_11yr")
    private Integer over11yr;

    // số người từ 6 đến 11 tuổi
    @Column(name = "6_to_11yr")
    private Integer from6to11yr;

    // số người dưới 6 tuổi
    @Column(name = "under_6yr")
    private Integer under6yr;

    @Column(name = "note", columnDefinition = "LONGTEXT")
    private String note;

    @ManyToOne
    @JoinColumn(name = "start_date_tour")
    private StartDateTour startDateTour;


    
}
