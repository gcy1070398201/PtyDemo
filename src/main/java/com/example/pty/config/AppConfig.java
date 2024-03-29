package com.example.pty.config;

import com.example.pty.interceptor.AppInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {


    @Autowired
    AppInterceptor appInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appInterceptor)
                .addPathPatterns("/**");
    }
//    /**
//     * 跨域支持
//     * @param registry
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .maxAge(3600 * 24);
//    }
}
