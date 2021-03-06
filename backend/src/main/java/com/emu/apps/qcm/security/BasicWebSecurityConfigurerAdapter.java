package com.emu.apps.qcm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity
//@Configuration
public class BasicWebSecurityConfigurerAdapter extends
        WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        try {
            auth.inMemoryAuthentication()
                    .withUser("user")  // #1
                    .password("password")
                    .roles("USER")
                    .and()
                    .withUser("admin") // #2
                    .password("password")
                    .roles("ADMIN", "USER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**"); // #3
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll() // #4
                //.antMatchers("/api/**", "/about").hasRole("USER") // #4
                .antMatchers("/admin/**").hasRole("ADMIN") // #6
                .anyRequest().authenticated() // 7
                .and()
                .formLogin()  // #8
                .loginProcessingUrl("/login") // #9
                .permitAll(); // #5
    }

}