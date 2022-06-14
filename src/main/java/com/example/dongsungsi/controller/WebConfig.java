package com.example.dongsungsi.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * packageName : com.example.dongsungsi.controller
 * fileName : WebConfig
 * author : jc
 * date : 2022-06-14
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-14         jc          최초 생성
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @CrossOrigin 어노테이션과 동일함
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        addMapping : springboot서버로 들어오는 모든 요청
        registry.addMapping("/**")
//                아래 주소로 들어오는 요청은 보안 허용
                .allowedOrigins("http://localhost:8081");
    }
}
