package com.example.app.models.token;

import com.example.app.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

public interface TokenRepository<T extends Token> extends JpaRepository<T, Long> {
    Optional<T> findByToken(String token);
    Optional<T> findByUserAndType(User user, String type);
}
