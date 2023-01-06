package com.example.app.controller;

import com.example.app.dto.PictureSubmition;
import com.example.app.dto.PictureSubmitionRequest;
import com.example.app.service.PictureService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pictures")
@RequiredArgsConstructor
public class PictureController {
    private final PictureService pictureService;

    @PostMapping
    public void submit(@RequestBody PictureSubmition pictureSubmition,HttpServletRequest httpServletRequest ) {
        var request = new PictureSubmitionRequest(pictureSubmition, httpServletRequest.getRemoteAddr());
        pictureService.submit(request);
    }


}
