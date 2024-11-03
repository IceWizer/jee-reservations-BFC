package com.bfv.reservation.repository;

import com.bfv.reservation.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        // Import via data.sql
        String email = "test_user-repository_find-by-email@icewize.fr";
        // Given

        // When
        User foundUser = this.userRepository.findByEmail(email).orElse(null);

        // Then
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals("test_user-repository_find-by-email", foundUser.getId());
        Assertions.assertEquals(email, foundUser.getEmail());
        Assertions.assertEquals("Test", foundUser.getFirstName());
        Assertions.assertEquals("User", foundUser.getLastName());
        Assertions.assertEquals("password", foundUser.getPassword());
        Assertions.assertNotNull(foundUser.getId());
    }
}
