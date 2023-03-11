package com.example.app.entities;

import com.example.app.models.user.User;
import com.example.app.models.user.Role;

import com.example.app.models.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserEntityRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFind() {

        User user = User.builder()
                .firstname("John")
                .lastname("Doe")
                .email("johndoe@example.com")
                .preferences("preferences")
                .confirmed(true)
                .role(Role.USER)
                .password("password")
                .locked(false)
                .enabled(true)
                .build();

        entityManager.persist(user);
        entityManager.flush();

        User found = userRepository.findByEmail("johndoe@example.com").orElse(null);

        assert found != null;
        assertEquals(found, user);
    }
}