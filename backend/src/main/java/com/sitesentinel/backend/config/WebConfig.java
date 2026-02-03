
package com.sitesentinel.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map http://localhost:8080/screenshots/... to the local folder
        registry.addResourceHandler("/screenshots/**")
                .addResourceLocations("file:screenshots/");
    }
}