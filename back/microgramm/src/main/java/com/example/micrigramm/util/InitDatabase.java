package com.example.micrigramm.util;

import com.example.micrigramm.entity.*;
import com.example.micrigramm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Random;

@Configuration
@RequiredArgsConstructor
public class InitDatabase {
    private final UserRepository user;
    private final PublicationRepository pub;
    private final CommentRepository comm;
    private final LikeRepository like;
    private final SubscriptionRepository sub;
    private final PasswordEncoder encoder;

    @Bean
    CommandLineRunner init(

    ) {
        return (args) -> {
            generateAndInsertData();
        };
    }

    private void generateAndInsertData() {

        sub.deleteAll();
        like.deleteAll();
        comm.deleteAll();
        pub.deleteAll();
        user.deleteAll();

        Random r = new Random();
        List<User> users = List.of(new User[]{
                User.builder().name("test").accountName("@test").email("test@mail.com").password(encoder.encode("qwe")).countPublications(0).countSubscribers(0).countSubscribes(0).build(),
                User.builder().name("quest").accountName("@quest").email("quest@mail.com").password(encoder.encode("qwe")).countPublications(0).countSubscribers(0).countSubscribes(0).build(),
                User.builder().name("admi").accountName("@admi").email("admi@mail.com").password(encoder.encode("qwe")).countPublications(0).countSubscribers(0).countSubscribes(0).build(),

        });


        user.saveAll(users);

    }
}
