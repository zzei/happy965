package com.zei.happy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 允许跨域
 */
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/friend/**")
                            .allowedOrigins("*")
                            .allowedMethods("GET","POST","PUT","DELETE")
                            .allowedHeaders("*")
                            .allowCredentials(true).maxAge(3600);
    }
}
