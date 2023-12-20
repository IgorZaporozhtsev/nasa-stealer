package com.example.nasapicturesstealer.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Camera {
    @Id
    private Long id;
    private Long nasa_id;
    private String name;
    private LocalDateTime createdAt = LocalDateTime.now();
    @OneToMany(mappedBy = "camera_id", cascade = CascadeType.ALL)
    private List<Picture> picture;
}
