package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //Agregar y personalizar usuarios
    //Autenticacion
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    //Definir tipo de isncripcion
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        //Crear un nuevo objeto de tipo BCrypt
        return new BCryptPasswordEncoder();
    }
    
    //Definir la utilizacion de userdetailservice y passwordencoder
    
    @Override
    public void configure(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    //Configuracion de los paths
    @Override
    //Autorizacion
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                //restringuir los paths
                .antMatchers("/editar/**", "/agregar/**", "/eliminar")
                .hasRole("ADMIN")
                .antMatchers("/")//Quien puede acceder cualquier usuario que contenga estos valores 
                .hasAnyRole("USER", "ADMIN", "MONICA")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/errores/403")
                ;
    }
}
