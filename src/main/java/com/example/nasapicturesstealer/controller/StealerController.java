package com.example.nasapicturesstealer.controller;

import com.example.nasapicturesstealer.service.StealService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("picture/steal")
@RequiredArgsConstructor
public class StealerController {

    private final StealService service;

    @PostMapping
    public void steal(@RequestBody StealRequest request) throws JsonProcessingException {
        service.stealNasaPicture(request);
    }

}
