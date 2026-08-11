package kodlamaio.northwind.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.PublicKey;

@Configuration //It tells Spring Boot that this is a configuration file.
@EnableWebSecurity//activates web security rules
public class SecurityConfig {

    @Bean // BCrypt
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    //decide the which adress publicly accessible which adress are require login.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/api/users/**").permitAll()
                                .requestMatchers("/api/products/getall").permitAll()

                                .anyRequest().authenticated()

                        );
        return http.build();
    }
}
