package com.jxnu.stras.config;

import com.jxnu.stras.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**","/person/**")  //所有请求都被拦截包括静态资源
                .excludePathPatterns("/","/user/**","/admin/css/**","/store/**","/admin/fonts/**","/admin/images/**","/admin/js/**","/user/css/**","/front/**","/js/**","/images/**"); //放行的请求
    }

}
