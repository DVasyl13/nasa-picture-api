package com.example.app.service;

import com.example.app.dto.PictureSubmissionRequest;
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
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final NasaClientService nasaClientService;

    public void submit(PictureSubmissionRequest request) {
        validateUserAlreadySubmitted(request);
        var user = validateUserData(request);
        validatePicture(request, user);
    }

    private void validatePicture(PictureSubmissionRequest request, User user) {
        var picture = request.pictureSubmission();
        if (picture.equals(nasaClientService.getLargestPicture())) {
            users.put(request.address(), user);
        } else {
            throw new IncorrectPictureException();
        }
    }

    private  User validateUserData(PictureSubmissionRequest request) {
        var user = request.pictureSubmission().user();
        if (ObjectUtils.isEmpty(user.firstName()) || ObjectUtils.isEmpty(user.lastName())) {
            throw new InvalidUserDataException();
        }
        return user;
    }

    private void validateUserAlreadySubmitted(PictureSubmissionRequest request) {
        if (users.containsKey(request.address())) {
            throw new CorrectPictureAlreadySubmittedException();
        }
    }

}
