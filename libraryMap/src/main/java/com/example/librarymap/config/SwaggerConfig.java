package com.example.librarymap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static ApiInfo DEFAULT = null;
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2);
    }
}
