package com.spingere.config;

import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author G13380
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    
    private @Autowired AppConfig appConfig;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService()).passwordEncoder(this.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // asegurar las urls deseadas
        http.authorizeRequests()
                .antMatchers("/", "/resources/**", "/login**").permitAll()
                .antMatchers("/users").hasAnyRole("ADMIN", "USER")
                .antMatchers("/users/**").authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/users").failureUrl("/login?error")
                .usernameParameter("user")
                .passwordParameter("pass")
                .and()
                .logout().logoutSuccessUrl("/login?logout");
    }
    
    @Override
    public UserDetailsService userDetailsService() {
        try {
            JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
            jdbcDaoImpl.setDataSource(appConfig.dataSource());
            jdbcDaoImpl.setUsersByUsernameQuery("select usuario, contrasena, activo from usuario where usuario=?");
            jdbcDaoImpl.setAuthoritiesByUsernameQuery("select u.usuario, ru.rolUsuario from usuario u, clienteusuario cu, rolusuario ru where u.idUsuario = cu.idUsuario and cu.idRolUsuario = ru.idRolUsuario and usuario=? and cu.idRolUsuario in( select distinct cu1.idRolUsuario from usuario u1, clienteusuario cu1 where u1.idUsuario = cu1.idUsuario and usuario=u.usuario);");
            return jdbcDaoImpl;
        } catch (NamingException ex) {
            logger.error("{{ no fue posible crear el validador de credenciales del sistema }}", ex);
            return null;
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

