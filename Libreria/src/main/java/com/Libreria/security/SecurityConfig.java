
package com.Libreria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/contacto", "/registro", "/login", "/logout").permitAll()
                .requestMatchers("/productos").permitAll() // 👈 Habilita acceso público a /productos
                .requestMatchers("/imagenes/**", "/css/**", "/js/**").permitAll() // recursos estáticos
                .requestMatchers("/client/**").hasRole("cliente")
                .requestMatchers("/admin/**").hasRole("admin")
                .anyRequest().authenticated()
        )
        .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(customSuccessHandler)
        )
        .logout(config -> config.logoutSuccessUrl("/"))
        .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

