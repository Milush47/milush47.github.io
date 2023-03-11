package com.example.app.services;

import com.example.app.models.user.User;
import com.example.app.models.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final UserRepository userRepository;

    public void uploadImage(MultipartFile image, User user) {
        try(InputStream is = image.getInputStream()) {
            Path filePath = Paths.get("avatar", image.getOriginalFilename());

            Files.copy(is, filePath, StandardCopyOption.REPLACE_EXISTING);

            user.setAvatarUrl(filePath.toString());

            userRepository.save(user);

        } catch(IOException ex) {
            throw new InternalError("ads");
        }
    }
}
