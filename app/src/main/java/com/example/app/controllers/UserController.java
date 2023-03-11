package com.example.app.controllers;

import com.example.app.dto.responses.SuccessResponse;
import com.example.app.dto.requests.UserRequest;
import com.example.app.dto.responses.UserResponse;
import com.example.app.models.user.User;
import com.example.app.models.user.UserRepository;
import com.example.app.services.StorageService;
import com.example.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final   UserService                 userService;
    private final   StorageService              storageService;
    @GetMapping
    public ResponseEntity<SuccessResponse> getUser(
            WebRequest request
    ) {
        User user = userService.getUserByJWT(request);

        UserResponse userResponse = userService.mapToUserResponse(user);

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("User details are provided")
                        .success(true)
                        .data(userResponse)
                        .build()
        );
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<Void> uploadImage(
            @RequestParam("imagePath")  MultipartFile   image,
                                        WebRequest      request
    ) {
        User user = userService.getUserByJWT(request);

        storageService.uploadImage(image, user);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(
            @RequestBody    UserRequest userRequest,
                            WebRequest  request
    ) {
        User user = userService.getUserByJWT(request);

        userService.updateUser(user, userRequest, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(
            WebRequest request
    ) {
        User user = userService.getUserByJWT(request);

        userService.delete(user);

        return ResponseEntity.ok().build();
    }
}