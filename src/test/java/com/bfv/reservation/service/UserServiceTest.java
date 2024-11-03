package com.bfv.reservation.service;

import com.bfv.reservation.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFindByEmail() {
        // given
        String email = "test_user-repository_find-by-email@icewize.fr";

        // when
        User user = userService.findByEmail(email).orElse(null);

        // then
        Assertions.assertNotNull(user);
        Assertions.assertEquals("test_user-repository_find-by-email", user.getId());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals("Test", user.getFirstName());
        Assertions.assertEquals("User", user.getLastName());
        Assertions.assertEquals("password", user.getPassword());
        Assertions.assertNotNull(user.getId());
    }
}
