package com.myproject.POS_System.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Maps all URLs starting with /images/ to the actual folder on the disk
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:D:/cmjd/Cmjd/POS-System/src/main/resources/static/images/");
    }
}