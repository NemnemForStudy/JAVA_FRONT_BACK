package com.nemnem.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    //# CORS( Cross - Origin Resouece Sharing ) 정책
    //? 다른 출처의 자원을 공유할 수 있도록 설정하는 권한 정책(그래서 다 허가를 해줘야함.)
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        //? 모든 back-end 경로 설정
        corsRegistry.addMapping("/**")
                    //? 모든 출처에 대해 허용
                    .allowedOrigins("*")
                    //? 모든 메서드에 대해 허용
                    .allowedMethods("*");
    }

}
