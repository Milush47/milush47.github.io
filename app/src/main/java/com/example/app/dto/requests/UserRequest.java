package com.example.app.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import org.springframework.data.web.JsonPath;
import org.springframework.web.multipart.MultipartFile;

public record UserRequest(
        @JsonProperty("firstname")
        String firstname,
        @JsonProperty("lastname")
        String lastname,
        @Email
        @JsonProperty("email")
        String email,
        @JsonProperty("avatar")
        MultipartFile avatar
) {
    @Builder public UserRequest {}
}
