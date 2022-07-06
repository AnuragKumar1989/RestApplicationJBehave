package com.assignment.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration for the NACE application.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket postApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
                .paths(Predicates.or((PathSelectors.regex("/api/nace.*")), (PathSelectors.regex("/actuator.*"))))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("NACE Operations")
                .description("NACE details")
                .build();
    }

}
