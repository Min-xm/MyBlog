package com.xm.blog.spro.config;

import com.xm.blog.spro.component.AdminHandlerInterceptor;
import com.xm.blog.spro.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            //添加视图映射
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/guest.html").setViewName("guest");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/admin.html").setViewName("admin");
            }
            @Override
            //注册拦截器
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/guest.html","/article/*","/admin.html","/class","/file","/blog");
                registry.addInterceptor(new AdminHandlerInterceptor()).addPathPatterns("/admin.html");
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/upload/**").addResourceLocations("file:E://images/");
            }
        };
        return webMvcConfigurer;
    }

}
