package com.pick.nalsoom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FeatureConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //시간측정
    /*@Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/

}
