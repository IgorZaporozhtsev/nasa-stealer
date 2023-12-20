package com.example.nasapicturesstealer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PictureResponse {
    @JsonProperty(value = "id")
    private Long nasaId;

    @JsonProperty(value = "img_src")
    private String imgSrc;

    @JsonProperty(value = "camera")
    private CameraResponse cameraResponse;
}
