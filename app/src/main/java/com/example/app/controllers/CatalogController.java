package com.example.app.controllers;

import com.example.app.services.CatalogService;
import com.example.app.services.JWTService;
import com.example.app.models.user.UserRepository;
import com.example.app.models.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*
    CatalogController is made for mapping following paths:
        - /catalog  (GET)

    It is responsible for showing all recipes for user.
    If user is authenticated the catalog page will show recipes based on user's preferences.
    If user isn't authenticated recipes will be shown randomly
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog")
public class CatalogController {
    // Autowired variables by constructor
    private final UserRepository    userRepository;
    private final JWTService        jwtService;
    private final CatalogService    catalogService;

    //RestTemplate is using for fetching SpoonacularAPI in this case
    private final RestTemplate      restTemplate    = new RestTemplate();

    @GetMapping
    public ResponseEntity<String> getAllRecipes(
            @RequestHeader(value = "Authorization", required = false) String authorization
    ) {
        // If user is authorized... (checks by jwt)
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String jwtToken = authorization.substring(7);
            String username = jwtService.extractUsername(jwtToken);

            User user = userRepository.findByEmail(username).orElseThrow(
                    () -> new UsernameNotFoundException("User not found")
            );

            return ResponseEntity.ok("ok");
        } else {
            // If user isn't authorized it used default uri for fetch recipes in random order
            String result = restTemplate.getForObject(catalogService.getUri(), String.class);

            return ResponseEntity.ok(result);
        }
    }
}
