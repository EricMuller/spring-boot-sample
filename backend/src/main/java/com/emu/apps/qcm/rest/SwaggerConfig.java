package com.emu.apps.qcm.rest;

import com.google.common.collect.*;
import org.springframework.context.annotation.*;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.data.rest.core.config.*;
import org.springframework.data.rest.core.mapping.*;
import org.springframework.data.rest.webmvc.config.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spi.service.contexts.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.*;

import java.util.*;

import static com.google.common.base.Predicates.*;
import static springfox.documentation.builders.PathSelectors.*;
import static springfox.documentation.builders.RequestHandlerSelectors.*;

@Configuration
@EnableSwagger2
@Import({springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class})
public class SwaggerConfig {

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(null, null, null, null, null, ApiKeyVehicle.HEADER, "Authorization", null);
    }

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurerAdapter() {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setRepositoryDetectionStrategy(
                        RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
            }
        };
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(or(withClassAnnotation(RestController.class),        // business services
                        withClassAnnotation(RepositoryRestResource.class))
                )
                //RequestHandlerSelectors.basePackage("com.emu.apps.qcm"))
                .paths(regex("/api/v1.*"))
                .paths(PathSelectors.any())
                .build()
                .enable(true)
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
                .forPaths(regex("/anyPath.*"))
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
                "https://www.apache.org/licenses/LICENSE-2.0", Lists.newArrayList());
        return apiInfo;
    }

}