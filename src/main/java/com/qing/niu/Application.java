package com.qing.niu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * <p>
 *     SpringBoot启动入口
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/9
 */
@SpringBootApplication(exclude = {SpringDataWebAutoConfiguration.class,
        FallbackWebSecurityAutoConfiguration.class,SpringBootWebSecurityConfiguration.class})
@ImportResource({"classpath:spring/application-context.xml"})
@ComponentScan(basePackages = {"web"})
public class Application extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(false);
        app.run(args);
    }
}
