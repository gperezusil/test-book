package com.test.exam.util.interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final HeaderValidationInterceptor headerValidationInterceptor;
    @Autowired
    public WebMvcConfig(HeaderValidationInterceptor headerValidationInterceptor) {
        this.headerValidationInterceptor = headerValidationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Registra tu interceptor y especifica los patrones de URL a los que se aplicará
        registry.addInterceptor(headerValidationInterceptor).addPathPatterns("/**");
    }

}
