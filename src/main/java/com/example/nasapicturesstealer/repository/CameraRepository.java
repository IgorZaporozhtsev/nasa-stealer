package com.example.nasapicturesstealer.repository;

import com.example.nasapicturesstealer.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, Long> {
}
