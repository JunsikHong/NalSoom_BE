package com.pick.nalsoom.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOrigins("http://localhost:3000", "http://openapi.seoul.go.kr:8088")
        .allowedHeaders("Authorization", "Content-Type")
        .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
        .allowCredentials(true);
    }
    
}
