package com.pweb.dulich.model;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //các trường khác bao gồm: tour_name, schedule, length, price, description, detail_schedule, note

    @Column(name = "tour_name")
    private String tourName;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "length")
    private String length;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description",columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "detail_schedule", columnDefinition = "LONGTEXT")
    private String detailSchedule;

    @Column(name = "note", columnDefinition = "LONGTEXT")
    private String note;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StartDateTour> startDates;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdviceRequest> adviceRequests;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images;
}
