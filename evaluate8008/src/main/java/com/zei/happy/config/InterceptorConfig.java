package com.zei.happy.config;

import com.zei.happy.interceptor.AdminInterceptor;
import com.zei.happy.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    public AdminInterceptor adminInterceptor(){
        return new AdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                                .addPathPatterns("/add");
        registry.addInterceptor(adminInterceptor())
                                .addPathPatterns("/delete");
    }
}
