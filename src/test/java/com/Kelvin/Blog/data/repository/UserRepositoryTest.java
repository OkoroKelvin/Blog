package com.Kelvin.Blog.data.repository;

import com.Kelvin.Blog.data.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void searchForUserByFirstNameAndLastName(){
        List<User> users = userRepository.findUserByFirstNameAndLastName("Kelvin", "Okoro");
        for (User user: users) {
            log.info(user.toString());
        }
    }

    @Test
    void userCanBeCreatedAndSaved(){

        User user1 = User.builder()
                .firstName("Steve")
                .lastName("Jobs")
                .email("Stevejobs@yahoo.com")
                .password("123$%^")
                .phoneNumber("08163091749").build();


        User user2 = User.builder()
                .firstName("Isaac")
                .lastName("Newton")
                .email("Isaacnewton@yahoo.com")
                .password("223$%^")
                .phoneNumber("08463091749").build();


        User user3 = User.builder()
                .firstName("Bill")
                .lastName("Gate")
                .email("Billgate@yahoo.com")
                .password("423$%^")
                .phoneNumber("08153091749").build();

        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);
        User savedUser3 = userRepository.save(user3);

        assertThat(savedUser1).isNotNull();
        assertThat(savedUser2).isNotNull();
        assertThat(savedUser3).isNotNull();
    }


}