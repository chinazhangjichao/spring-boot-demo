package cn.bdqn.springbootdemo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.WebMvcRequestHandler;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-05-02 10:30
 * @since JDK 1.8
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        //给docket上下文配置api描述信息
        docket.apiInfo(apiInfo());
        docket = docket
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.bdqn.springbootdemo.controller"))//用于配置，扫描哪个子包下的注解
                .paths(
                        PathSelectors.any()
                )
                .build();
        return docket;
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("新闻管理系统api")
                .description("新闻管理系统API接口")
                .version("1.0")
                .build();
    }




    @Bean
    public  static BeanPostProcessor beanPostProcessor(){
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if(bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider){
                    Field field = ReflectionUtils.findField(bean.getClass(),"handlerMappings");
                    field.setAccessible(true);
                    try {
                        List<RequestMappingInfoHandlerMapping> list= (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                        list.removeIf(m->m.getPatternParser()!=null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return bean;
            }
        };
    }
}
