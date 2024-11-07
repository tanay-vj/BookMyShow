package com.bms.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SignupResponseDto {
    private Long userId;
    private HttpStatus httpStatus;

    public SignupResponseDto(Long id, HttpStatus accepted) {
        this.userId = id;
        this.httpStatus = accepted;
    }
}
