package ru.expogroup.HT5.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(registry -> registry
                        // для администратора полные права
                        .requestMatchers("db/readers").hasAuthority("admin")
                                .requestMatchers("db/issues").hasAuthority("admin")
                                // для пользователей доступен только список книг
                        .requestMatchers("db/books").authenticated()
                        .anyRequest().denyAll()
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
