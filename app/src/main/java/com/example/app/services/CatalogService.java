package com.example.app.services;

import org.springframework.stereotype.Service;


@Service
public class CatalogService {
    private final String URI        = "https://api.spoonacular.com/recipes/complexSearch";
    private final String API_KEY    = "32dba3c0bff74fe88533bb183e6a0525";

    public String getUri() {
        return URI + "&" + API_KEY;
    }
}
