package com.pick.nalsoom.config;

import com.pick.nalsoom.aop.TimeTraceAop;
import com.pick.nalsoom.jwt.JwtTokenProvider;
import com.pick.nalsoom.repository.*;
import com.pick.nalsoom.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;


@Configuration
public class SpringConfig {

    //data jpa -> 자동 빈등록 되어있음 -> 주입만 하면 됨
    private final FavoritesRepository favoritesRepository;
    private final GoodRepository goodRepository;
    private final NotificationRepository notificationRepository;
    private final ReviewRepository reviewRepository;
    private final ShelterRepository shelterRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ShelterViewRepository shelterViewRepository;

    @Autowired
    public SpringConfig(FavoritesRepository favoritesRepository, GoodRepository goodRepository, NotificationRepository notificationRepository, ReviewRepository reviewRepository, ShelterRepository shelterRepository, UserRepository userRepository, PostRepository postRepository, ShelterViewRepository shelterViewRepository) {
        this.favoritesRepository = favoritesRepository;
        this.goodRepository = goodRepository;
        this.notificationRepository = notificationRepository;
        this.reviewRepository = reviewRepository;
        this.shelterRepository = shelterRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.shelterViewRepository = shelterViewRepository;
    }

    @Bean
    public UserService userService(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        return new UserService(userRepository, passwordEncoder, jwtTokenProvider);
    }

    @Bean
    public PostService postService(UserService userService, ShelterService shelterService) {
        return new PostService(postRepository, userService, shelterService);
    }

    @Bean
    public ShelterViewService shelterViewService () {
        return new ShelterViewService(shelterViewRepository);
    }

    @Bean
    public GoodService goodService(UserService userService, ShelterService shelterService) {
        return new GoodService(goodRepository, userService, shelterService);
    }

    @Bean
    public ReviewService reviewService(UserService userService, ShelterService shelterService) {
        return new ReviewService(reviewRepository, userService, shelterService);
    }

    @Bean
    public FavoritesService favoritesService(UserService userService) {
        return new FavoritesService(favoritesRepository, userService);
    }

    @Bean
    public NotificationService notificationService(UserService userService) {
        return new NotificationService(notificationRepository, userService);
    }

    @Bean
    public ShelterService shelterService(RestTemplate restTemplate) {
        return new ShelterService(shelterRepository, restTemplate);
    }
}
