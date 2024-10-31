package com.pick.nalsoom.config;

import com.pick.nalsoom.aop.TimeTraceAop;
import com.pick.nalsoom.repository.*;
import com.pick.nalsoom.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class SpringConfig {

    //data jpa -> 자동 빈등록 되어있음 -> 주입만 하면 됨
    private final FavoritesRepository favoritesRepository;
    private final GoodRepository goodRepository;
    private final NotificationRepository notificationRepository;
    private final ReviewRepository reviewRepository;
    private final ShelterRepository shelterRepository;
    private final ShelterBoardRepository shelterBoardRepository;
    private final UserRepository userRepository;

    @Autowired
    public SpringConfig(FavoritesRepository favoritesRepository, GoodRepository goodRepository, NotificationRepository notificationRepository, ReviewRepository reviewRepository, ShelterRepository shelterRepository, ShelterBoardRepository shelterBoardRepository, UserRepository userRepository) {
        this.favoritesRepository = favoritesRepository;
        this.goodRepository = goodRepository;
        this.notificationRepository = notificationRepository;
        this.reviewRepository = reviewRepository;
        this.shelterRepository = shelterRepository;
        this.shelterBoardRepository = shelterBoardRepository;
        this.userRepository = userRepository;
    }

    @Bean
    public FavoritesService favoritesService(UserService userService) {
        return new FavoritesService(favoritesRepository, userService);
    }

    @Bean
    public GoodService goodService(UserService userService, ShelterService shelterService) {
        return new GoodService(goodRepository, userService, shelterService);
    }

    @Bean
    public NotificationService notificationService(UserService userService) {
        return new NotificationService(notificationRepository, userService);
    }

    @Bean
    public ReviewService reviewService(UserService userService, ShelterService shelterService) {
        return new ReviewService(reviewRepository, userService, shelterService);
    }

    @Bean
    public ShelterBoardService shelterBoardService(UserService userService) {
        return new ShelterBoardService(shelterBoardRepository, userService);
    }

    @Bean
    public ShelterService shelterService() {
        return new ShelterService(shelterRepository);
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository);
    }

    //시간측정
    /*@Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/

    //restTemplate
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
