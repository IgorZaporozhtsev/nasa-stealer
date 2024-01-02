package com.example.nasapicturesstealer.service;

import com.example.nasapicturesstealer.controller.StealRequest;
import com.example.nasapicturesstealer.dto.CameraResponse;
import com.example.nasapicturesstealer.dto.NasaResponse;
import com.example.nasapicturesstealer.dto.PictureResponse;
import com.example.nasapicturesstealer.model.Camera;
import com.example.nasapicturesstealer.model.Picture;
import com.example.nasapicturesstealer.repository.CameraRepository;
import com.example.nasapicturesstealer.webclinet.NasaClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StealService {
    private final static String KEY = "fA0EPfEXrhi7fMNaNxpkOlm607vJdJkPDg9VpWSv";

    private final NasaClient nasaClient;
    private final ObjectMapper objectMapper;
    private final CameraRepository cameraRepository;

    public void stealNasaPicture(StealRequest request) throws JsonProcessingException {
        String content = nasaClient.receivePicture(KEY, request.sol());
        JsonNode node = objectMapper.readValue(content, JsonNode.class);
        NasaResponse nasaResponse =  objectMapper.treeToValue(node, NasaResponse.class);

        Map<CameraResponse, List<PictureResponse>> map = nasaResponse.getPhotos().stream()
                .collect(Collectors.groupingBy(PictureResponse::getCameraResponse));

        for (Map.Entry<CameraResponse, List<PictureResponse>> entry: map.entrySet()) {
            CameraResponse cameraResponse = entry.getKey();
            List<Picture> pictureResponses = toPicture(entry.getValue());

            Camera camera = new Camera();
            camera.setId(cameraResponse.getId());
            camera.setName(cameraResponse.getName());
            camera.setPicture(pictureResponses);

            saveCameraData(camera);
        }
    }

    private List<Picture> toPicture(List<PictureResponse> pictureResponses) {
        return pictureResponses.stream()
                .map(pictureResponse -> {
                    Picture picture = new Picture();
                    picture.setId(new Random().nextLong());
                    picture.setNasa_id(picture.getNasa_id());
                    picture.setCamera_id(pictureResponse.getCameraResponse().getId());
                    picture.setImd_src(pictureResponse.getImgSrc());
                    return picture;

                }).toList();

    }

    @Transactional
    public void saveCameraData(Camera camera){
        cameraRepository.save(camera);
    }

}
