package com.emu.apps.qcm.security;


import org.slf4j.*;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.security.*;
import org.springframework.boot.autoconfigure.security.oauth2.resource.*;
import org.springframework.boot.context.properties.*;
import org.springframework.boot.web.servlet.*;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.*;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.filter.*;
import org.springframework.security.oauth2.client.token.grant.code.*;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.www.*;
import org.springframework.security.web.csrf.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.*;

import javax.servlet.*;
import java.util.*;

@EnableWebSecurity
@Configuration
@EnableOAuth2Client
@RestController
@Order(6)
@Profile(value = {"development", "production"})
public class Auth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private OAuth2ClientContext oauth2ClientContext;
    @Autowired
    private JwtTokenAuthenticationService jwtTokenAuthenticationService;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    @ConfigurationProperties("github")
    public ClientResources github() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("webmarks")
    public ClientResources webmarks() {
        return new ClientResources();
    }

    @Bean
    @Scope("prototype")
    Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/v2/api-docs", "/swagger-resources/configuration/ui", "/configuration/ui"
                , "/swagger-resources**", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        Filter oauth2LoginFilter = oauth2ClientFilter();

        http
                //.antMatcher("/login/local?error=access_denied").authorizeRequests().anyRequest().
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/webjars/**", "/**.js", "/*.ico", "/*.map", "/assets/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                //.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
                .authenticationEntryPoint(new Http401AuthenticationEntryPoint("Bearer"))
                .and()
                /*.logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID", JwtTokenCst.HEADER_AUTHORIZATION)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll()
                .and() */
                //.httpBasic().disable()
                //.formLogin().disable()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                // auth2 login
                .addFilterBefore(oauth2LoginFilter, BasicAuthenticationFilter.class)
                // jwt Token based authentication based on the header previously given to the client
                .addFilterBefore(new JwtAuthentificationFilter(jwtTokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        ;

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

        public AuthorizationCodeResourceDetails getClient() {
            return client;
        }

        public ResourceServerProperties getResource() {
            return resource;
        }
    }

}