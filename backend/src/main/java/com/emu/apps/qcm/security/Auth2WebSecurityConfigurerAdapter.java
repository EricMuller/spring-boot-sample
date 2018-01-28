package com.emu.apps.qcm.security;


import com.emu.apps.qcm.model.Profile;
import com.emu.apps.qcm.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableWebSecurity
@Configuration
@EnableOAuth2Client
@RestController
@Order(6)
@org.springframework.context.annotation.Profile(value = {"development", "production"})
public class Auth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    protected static final Logger LOGGER = LoggerFactory.getLogger(Auth2WebSecurityConfigurerAdapter.class);

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private JwtTokenAuthenticationService jwtTokenAuthenticationService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private PrincipalExtractor principalExtractor;

    @Bean
    @ConfigurationProperties("github")
    public ClientResources github() {
        return new ClientResources(principalExtractor);
    }

    @Bean
    @ConfigurationProperties("webmarks")
    public ClientResources webmarks() {
        return new ClientResources(principalExtractor);
    }

    @Bean
    @Scope("prototype")
    Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.  getMethodParameter().getContainingClass());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/v2/api-docs", "/swagger-resources/configuration/ui", "/configuration/ui"
                , "/swagger-resources**", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**",
                                   "/metrics/**"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        Filter oauth2LoginFilter = oauth2ClientFilter();

        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/webjars/**", "/**.css", "/**.js", "/*.ico", "/*.map", "/assets/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new Http401AuthenticationEntryPoint("Bearer"))
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                // auth2 login
                .addFilterBefore(oauth2LoginFilter, BasicAuthenticationFilter.class)
                // jwt Token based authentication based on the header previously given to the client
                .addFilterBefore(new JwtAuthentificationFilter(jwtTokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    private Filter oauth2ClientFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(oauth2ClientFilter(github(), "/login/github"));
        filters.add(oauth2ClientFilter(webmarks(), "/login/webmarks"));
        filter.setFilters(filters);

        return filter;
    }

    private Filter oauth2ClientFilter(ClientResources client, String path) {
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);

        UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(),
                                                                        client.getClient().getClientId());
        tokenServices.setRestTemplate(oAuth2RestTemplate);
        tokenServices.setPrincipalExtractor(client.getPrincipalExtractor());

        OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationFilter = new OAuth2ClientAuthenticationProcessingFilter(path);
        oAuth2ClientAuthenticationFilter.setRestTemplate(oAuth2RestTemplate);
        oAuth2ClientAuthenticationFilter.setTokenServices(tokenServices);

        oAuth2ClientAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        return oAuth2ClientAuthenticationFilter;
    }

    class ClientResources {


        @NestedConfigurationProperty
        private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

        @NestedConfigurationProperty
        private ResourceServerProperties resource = new ResourceServerProperties();

        @NestedConfigurationProperty
        private PrincipalExtractor principalExtractor;


        public ClientResources(PrincipalExtractor principalExtractor) {
            this.principalExtractor = principalExtractor;
        }

        public void setPrincipalExtractor(PrincipalExtractor principalExtractor) {
            this.principalExtractor = principalExtractor;
        }

        public AuthorizationCodeResourceDetails getClient() {
            return client;
        }

        public ResourceServerProperties getResource() {
            return resource;
        }

        public PrincipalExtractor getPrincipalExtractor() {
            return principalExtractor;
        }

    }

    @Bean
    public PrincipalExtractor principalExtractor(ProfileService userService) {
        return map -> {
            String principalId = (String) map.get("email");
            Profile profile = userService.findByPrincipalId(principalId);
            if (profile == null) {
                LOGGER.info("No profile found, generating profile for {}", principalId);
                profile = new Profile();
                profile.setPrincipalId(principalId);
                profile.setCreated(new Date());
                profile.setEmail((String) map.get("email"));
                profile.setFullName((String) map.get("name"));
                profile.setPhoto((String) map.get("picture"));
                profile.setLoginType(TypeLogin.WEBMARKS.name());
                profile.setLastLogin(new Date());
            } else {
                profile.setLastLogin(new Date());
            }
            return userService.save(profile);
        };
    }

}