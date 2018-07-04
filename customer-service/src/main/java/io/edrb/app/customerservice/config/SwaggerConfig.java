package io.edrb.app.customerservice.config;

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
     * Creates a bean with customer API configuration
     *
     * @return Docket object
     */
    @Bean
    public Docket customerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("customer-api")
                .forCodeGeneration(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.edrb.app.customerservice.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Add API Info
     *
     * @return Customer service info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Customer Service REST API")
                .version("1.0.0")
                .build();
    }

}
