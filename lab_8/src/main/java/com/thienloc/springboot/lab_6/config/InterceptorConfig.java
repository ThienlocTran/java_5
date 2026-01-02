package com.thienloc.springboot.lab_6.config;

import com.thienloc.springboot.lab_6.Interceptor.AuthInterceptor;
import com.thienloc.springboot.lab_6.Interceptor.LogInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    
    final
    AuthInterceptor authInterceptor;
    
    final
    LogInterceptor logInterceptor;

    public InterceptorConfig(AuthInterceptor authInterceptor, LogInterceptor logInterceptor) {
        this.authInterceptor = authInterceptor;
        this.logInterceptor = logInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // AuthInterceptor - Bảo mật các trang yêu cầu đăng nhập (PHẢI CHẠY TRƯỚC)
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/admin/**",
                        "/account/change-password", 
                        "/account/edit-profile",
                        "/account/**",
                        "/order/**")
                .excludePathPatterns("/admin/home/index")
                .order(1); // Chạy trước
        
        // LogInterceptor - Ghi log cho các trang được bảo mật (chỉ chạy sau khi đã đăng nhập)
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/admin/**",
                        "/account/**",
                        "/order/**")
                .excludePathPatterns("/admin/home/index")
                .order(2); // Chạy sau
    }
}
