package com.pweb.dulich.model;

import lombok.*;
import jakarta.persistence.*;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_name")
    private String imageName;

    //mỗi tour gồm 4 ảnh đánh số từ 1 đến 4
    @Column(name = "image_number")
    private int imageNumber;

    @Lob
    @Column(name = "image_data",columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;
    
}
