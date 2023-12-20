package com.example.nasapicturesstealer.controller;

import com.example.nasapicturesstealer.service.StealService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("picture/steal")
public class StealerController {

    @Autowired
    StealService service;

    @PostMapping
    public void steal(@RequestBody StealRequest request) throws JsonProcessingException {
        service.stealNasaPicture(request);
    }

}
