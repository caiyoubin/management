package com.example.web.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        final LoginInterceptor loginInterceptor = new LoginInterceptor();
        final AuthorityInterceptor authorityInterceptor = new AuthorityInterceptor();
        registry.addInterceptor(loginInterceptor).excludePathPatterns("login").addPathPatterns("/**");
        registry.addInterceptor(authorityInterceptor).excludePathPatterns("login").addPathPatterns("/**");
    }
}
