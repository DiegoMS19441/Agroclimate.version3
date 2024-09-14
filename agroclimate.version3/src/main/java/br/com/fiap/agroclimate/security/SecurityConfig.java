package br.com.fiap.agroclimate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    SecurityFilter securityFilter;
    private static final String[] SWAGGER_WHITELIS = { "/swagger-ui/**", "/v3/api-docs/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "solos/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "safras/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "colheitas/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "fazendas/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "infoplantacoes/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "plantacoes/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "agricultores/**").permitAll()




                        .requestMatchers(HttpMethod.DELETE, "solos/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "safras/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "colheitas/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "fazendas/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "infoplantacoes/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "plantacoes/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "agricultores/**").permitAll()



                        .requestMatchers(HttpMethod.GET, "solos/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "safras/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "colheitas/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "fazendas/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "infoplantacoes/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "plantacoes/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "agricultores/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "agricultores/**").permitAll()



                        .requestMatchers(HttpMethod.PUT, "solos/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "safras/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "colheitas/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "fazendas/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "infoplantacoes/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "plantacoes/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "agricultores/**").permitAll()


                        .requestMatchers(SWAGGER_WHITELIS).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
