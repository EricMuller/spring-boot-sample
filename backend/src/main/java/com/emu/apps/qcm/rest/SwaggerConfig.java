package com.emu.apps.qcm.rest;

import com.google.common.collect.*;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spi.service.contexts.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.*;

import java.util.*;

import static springfox.documentation.builders.PathSelectors.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(null, null, null, null, null, ApiKeyVehicle.HEADER, "Authorization", null);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.emu.apps.qcm.rest"))
                //.paths(regex("/bookmarks.*"))
                .paths(regex("/api.*"))
                .build().enable(true)
                .apiInfo(metaData())
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))

                ;

    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/anyPath.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("AUTHORIZATION", authorizationScopes));
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Questionnaire REST API",
                "Questionnaire REST API for webmarks.net",
                "1.0",
                "Terms of service",
                new Contact("Eric MULLER", "https://webmarks.net/questionnaires/api", "e.mul@free.fr"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }

}