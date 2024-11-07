package com.bms.controllers;

import com.bms.dtos.SignupRequestDto;
import com.bms.dtos.SignupResponseDto;
import com.bms.models.User;
import com.bms.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    private UserService userService;

    public SignupResponseDto signUp(SignupRequestDto request) {

        User user = userService.createUser(request);
        return new SignupResponseDto(user.getId(), HttpStatus.ACCEPTED);


    }
}
