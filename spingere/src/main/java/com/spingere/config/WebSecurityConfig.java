package com.spingere.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author G13380
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // almacenar y administrar las credenciales de usuario
        auth.inMemoryAuthentication()
                .withUser("test")
                .password("test")
                .authorities("ROLE_USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // asegurar las urls deseadas
        http.authorizeRequests()
                .antMatchers("/", "/resources/**", "/login**").permitAll()
                .antMatchers("/users").hasRole("USER")
                .antMatchers("/users/**").authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/users").failureUrl("/login?error")
                .usernameParameter("user")
                .passwordParameter("pass")
                .and()
                .logout().logoutSuccessUrl("/login?logout");
    }
    
}
