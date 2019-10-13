package com.cgi.smartcv.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket productApi() {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization") // name of header
                .modelRef(new ModelRef("string")).parameterType("header") // type - header
                .defaultValue("Bearer em9uZTpteXBhc3N3b3Jk") // based64 of - zone:mypassword
                .required(true) // for compulsory
                .build();
        List<Parameter> aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)//
                .select()//
                .apis(RequestHandlerSelectors.basePackage("com.cgi.smartcv.api"))//
                .paths(PathSelectors.any())//
                .build()//
                .apiInfo(apiEndPointsInfo())//
                .useDefaultResponseMessages(false)//
                .securitySchemes(new ArrayList<>(
                        Arrays.asList(new ApiKey("Bearer em9uZTpteXBhc3N3b3Jk", "Authorization", "Header"))))
                .pathMapping("").globalOperationParameters(aParameters);//
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Smart CV - SBP Bungalowparken BV")
                .description("Documentation Management REST API").version("0.1.1 BETA").build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
