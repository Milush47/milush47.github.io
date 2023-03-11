package com.example.app.dto.responses;

import lombok.Builder;

/*
    SuccessResponse is used for sending responses to frontend with piece of information
 */
public record SuccessResponse(
        String  message,
        boolean success,
        Object  data
) {
    @Builder public SuccessResponse { }

    public SuccessResponse(String message, boolean success) {
        this(message, success, null);
    }

    public boolean  isSuccess() {
        return success;
    }
}
