package cn.bdqn.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author ZJC
 * @decription:
 * @date: 2022-04-22 11:14
 * @since JDK 1.8
 */
@SpringBootApplication
@ServletComponentScan

public class SpringBootDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class,args);
    }
}
