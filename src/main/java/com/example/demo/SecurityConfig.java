package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/hello").hasRole("A")
                    .and()
                .httpBasic()
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .and()
                .csrf().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails john =
                User.withDefaultPasswordEncoder()
                .username("john")
                .password("password")
                .roles("A")
                .build();

        UserDetails jane =
                User.withDefaultPasswordEncoder()
                        .username("jane")
                        .password("password")
                        .roles("B")
                        .build();

        ArrayList<UserDetails> users = new ArrayList<UserDetails>();
        users.add(john);
        users.add(jane);

        return new InMemoryUserDetailsManager(users);
    }
}
