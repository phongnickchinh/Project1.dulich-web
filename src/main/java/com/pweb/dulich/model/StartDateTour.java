package com.pweb.dulich.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartDateTour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date", columnDefinition = "DATE")
    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @OneToMany(mappedBy = "startDateTour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookingRequest> bookingRequests;
    
}
