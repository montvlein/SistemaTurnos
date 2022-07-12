package com.dh.proyecto.Config;

import com.dh.proyecto.Services.implement.AppUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class Seguridad {

    @Autowired
    private AppUserServices services;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/", "/v1/**").permitAll()
                .anyRequest()
                .authenticated()

                .and().formLogin()
                .defaultSuccessUrl("/success") //si se pone el segundo parametro en true entonces siempre carga segun los valores del rol
                // pero para admin siempre carga la vista de admin, aunque se entre desde el link de user
                // si no se pone el segundo parametro, entonces al entrar desde los path /admin o /user entonces pide
                // hacer un loggin pero tira una respuesta 403... y si desde el menu de inicio se va directo a /login
                // entonecs dependiendo si el usuario tiene rol de admin o user va a la web correspondiente
                // porque el metodo 'rederPorRol' denderiza la web segun el rol... pero no quiere decir que tengan
                // autorizacion para entrar a su path correspondiente...
                .and().logout();

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }
}
