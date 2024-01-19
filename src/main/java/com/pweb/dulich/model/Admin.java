package com.pweb.dulich.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    
}
