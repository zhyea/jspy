package org.chobit.jspy.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        SessionInterceptor sessionInterceptor = new SessionInterceptor();
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/", "/**/app/**", "/**/user/**", "/**/receive", "/css/**", "/js/**", "/fonts/**");
    }
}
