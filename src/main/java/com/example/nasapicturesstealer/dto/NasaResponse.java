package com.example.nasapicturesstealer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NasaResponse {
    private List<PictureResponse> photos;
}
