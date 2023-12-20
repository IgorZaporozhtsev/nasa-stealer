package com.example.nasapicturesstealer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Picture {
    @Id
    private Long id;
    private Long nasa_id;
    private Long camera_id;
    private String imd_src;
    private LocalDateTime createdAt = LocalDateTime.now();
}
