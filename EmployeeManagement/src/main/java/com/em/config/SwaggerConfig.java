package com.em.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.em.services.UserManagementImpl;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger for restful testing. Access via:
 * http://localhost:8080/swagger-ui.html
 * 
 * @author Thang Le
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2).select()
		.apis(RequestHandlerSelectors.basePackage(UserManagementImpl.class.getPackage().getName()))
		.paths(PathSelectors.any()).build();
    }
}
