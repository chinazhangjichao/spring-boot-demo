package cn.bdqn.springbootdemo.config;

import cn.bdqn.springbootdemo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-26 11:13
 * @since JDK 1.8
 */
@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {

    @Bean
    LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/news/**").excludePathPatterns("/login");
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowCredentials(true)
//                .allowedOrigins(new String[]{"http://127.0.0.1:8848"})//允许哪台服务器访问我
//                .allowedMethods(new String[]{"GET","POST"})//方法名必须大写
//                .allowedHeaders(new String[]{"*"})
//                .maxAge(3600);
//    }
}
