package com.example.app.service;

import com.example.app.dto.Picture;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class NasaClientService {
    private final RestTemplate restTemplate;
    @Value("${nasa.api.url}")
    private String nasaApiUrl;

    public Picture getLargestPicture() {
        var jsonResponse = restTemplate.getForObject(nasaApiUrl, JsonNode.class);
        return StreamSupport.stream(jsonResponse.get("photos").spliterator(), true)
                .map(photo -> photo.get("img_src"))
                .map(JsonNode::asText)
                .map(this::createPicture)
                .max(Comparator.comparing(Picture::size))
                .orElseThrow();
    }

    private Picture createPicture(String url) {
        var pictureLocation = restTemplate.headForHeaders(url)
                .getLocation();
        var pictureSize = restTemplate.headForHeaders(pictureLocation)
                .getContentLength();
        return new Picture(url, pictureSize);
    }
}
