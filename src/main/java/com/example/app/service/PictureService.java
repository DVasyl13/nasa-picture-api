package com.example.app.service;

import com.example.app.dto.PictureSubmitionRequest;
import com.example.app.dto.User;
import com.example.app.exception.CorrectPictureAlreadySubmittedException;
import com.example.app.exception.IncorrectPictureException;
import com.example.app.exception.InvalidUserDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class PictureService {
    private Map<String, User> users = new ConcurrentHashMap<>();
    private final NasaClientService nasaClientService;

    public void submit(PictureSubmitionRequest request) {

        if (users.containsKey(request.address())) {
            throw new CorrectPictureAlreadySubmittedException();
        }

        var user = request.pictureSubmition().user();
        if (ObjectUtils.isEmpty(user.firstName()) || ObjectUtils.isEmpty(user.lastName())) {
            throw new InvalidUserDataException();
        }

        var picture = request.pictureSubmition();
        if (picture.equals(nasaClientService.getLargestPicture())) {
            users.put(request.address(), user);
        } else {
            throw new IncorrectPictureException();
        }
    }

}
