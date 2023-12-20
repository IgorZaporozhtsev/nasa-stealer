package com.example.nasapicturesstealer.webclinet;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/rovers/curiosity/photos")
public interface NasaClient {

    @GetExchange()
    String receivePicture(@RequestParam String api_key, @RequestParam String sol);
}
