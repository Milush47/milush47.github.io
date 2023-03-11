package com.example.app.dto.responses;

import lombok.Builder;

import java.util.Date;

/*
    ErrorMessage is used for wrap error message in easy way for frontend
 */
public record ErrorMessage(
        int     statusCode,
        Date    timeStamp,
        String  message,
        String  description
) {
    @Builder public ErrorMessage {}
}
