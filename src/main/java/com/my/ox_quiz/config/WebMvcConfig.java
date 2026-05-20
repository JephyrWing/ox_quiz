package com.my.ox_quiz.config;

import com.my.ox_quiz.interceptor.AdminInterceptor;
import com.my.ox_quiz.interceptor.LoginInterceptor;
import com.my.ox_quiz.interceptor.StatusInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final AdminInterceptor adminInterceptor;
    private final LoginInterceptor loginInterceptor;
    private final StatusInterceptor statusInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(loginInterceptor)
                .addPathPatterns("/**",
                        "/quiz/play",
                        "/quiz/check")
                .excludePathPatterns("/",
                        "/member/login",
                        "/member/join");
        registry
                .addInterceptor(adminInterceptor)
                .addPathPatterns("/quiz/**", "/admin/**")
                .excludePathPatterns("/quiz/play", "/quiz/check");
        registry
                .addInterceptor(statusInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/",
                        "/member/login",
                        "/member/join",
                        "/member/my-page",
                        "/member/password");
    }
}
