package io.edrb.app.orderservice.config;

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
 * Swagger configuration class
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Creates a bean with order API configuration
     *
     * @return Docket object
     */
    @Bean
    public Docket orderApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("order-api")
                .forCodeGeneration(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.edrb.app.orderservice.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Add API Info
     *
     * @return Order service info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Order Service REST API")
                .version("1.0.0")
                .build();
    }

}
