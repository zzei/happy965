package com.zei.happy.config;

import com.zei.happy.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置自定义登录拦截器
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor())
//                                .addPathPatterns("/friend/**")
//                                .excludePathPatterns("/friend/login","/friend/reg");
    }
}
