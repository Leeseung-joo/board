package com.codingrecipe.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration //설정을 하는메소드
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "/upload/**";
    String savePath = "C:/development/intellij_ultimate/spring_upload_files/";
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}
