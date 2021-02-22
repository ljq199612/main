package com.rz.iot.bpo.framework.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author by xuxiake, Date on 2020/6/9 15:18.
 * PS: Not easy to write code, please indicate.
 * Description：Swagger配置
 */
@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "swagger")
@Getter
@Setter
public class SwaggerConfig {

    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String termsOfServiceUrl;

    private Contact contact = new Contact();


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Contact {
        private String name;
        private String url;
        private String email;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .contact(new springfox.documentation.service.Contact(contact.getName(), contact.getUrl(), contact.getEmail()))
                .termsOfServiceUrl(termsOfServiceUrl)
                .version("1.0")
                .build();
    }
}
