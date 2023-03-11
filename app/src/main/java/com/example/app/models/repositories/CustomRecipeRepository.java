package com.example.app.models.repositories;

import com.example.app.models.entities.CustomRecipes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomRecipeRepository extends JpaRepository<CustomRecipes, Long> {
}
