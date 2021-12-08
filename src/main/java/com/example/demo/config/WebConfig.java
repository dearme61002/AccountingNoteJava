package com.example.demo.config;

import com.example.demo.filter.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
     //add filter 攔截所有請求 但登入與首頁 靜態資源例外
        registry.addInterceptor(new LoginFilter()).addPathPatterns("/**").excludePathPatterns("/","/index.html","/LoginPage","/css/**","/html/*","/img/**","/vendor/**","/login");
    }
}
