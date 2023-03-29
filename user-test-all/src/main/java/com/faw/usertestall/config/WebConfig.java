package com.faw.usertestall.config;

import com.faw.usertestall.interceptor.RateLimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Web配置
 *
 * @author 鹿胜宝
 * @date 2023/03/22
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private RateLimitInterceptor rateLimitInterceptor;

    /**
     * 添加拦截器
     *
     * @param registry 注册表
     * @author 鹿胜宝
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitInterceptor).addPathPatterns("/api/**");
    }

    /**
     * 添加资源处理程序
     * 实现ResourceHandlerRegistry添加一个新的资源处理程序。
     * addResourceHandler方法接受一个URL模式，用于指定资源的URL路径，而addResourceLocations方法则指定资源所在的目录。
     * 在这种情况下，我们将所有请求映射到/uploads/**到file:D:/WorkSpace/alibaba-faw-source/uploads/文件。
     * 如localhost:8080/uploads/1.txt即访问D:/WorkSpace/alibaba-faw-source/uploads/1.txt。
     *
     * @param registry 注册表
     * @author 鹿胜宝
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:D:/WorkSpace/alibaba-faw-source/uploads/");
    }
}
