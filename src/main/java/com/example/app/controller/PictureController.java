package com.example.app.controller;

import com.example.app.dto.PictureSubmission;
import com.example.app.dto.PictureSubmissionRequest;
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
    public void submit(@RequestBody PictureSubmission pictureSubmission, HttpServletRequest httpServletRequest ) {
        var request = new PictureSubmissionRequest(pictureSubmission, httpServletRequest.getRemoteAddr());
        pictureService.submit(request);
    }


}
