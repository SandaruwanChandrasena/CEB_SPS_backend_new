//package com.example.SPSProjectBackend.security;
//
//import org.springframework.boot.CommandLineRunner; // Added for printing the password at startup
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager; // Added for default user
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.List;
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig {
//
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(Customizer.withDefaults())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/**").permitAll()
//                        .requestMatchers("/api/v1/verify/**").permitAll()
//                        .requestMatchers("/api/report/**").permitAll()
//                        .requestMatchers("/api/application/**").permitAll()
//                        .requestMatchers("/api/spestcnd/**").permitAll()
//                        .requestMatchers("/api/v1/login").permitAll()
//                        .requestMatchers("/api/v1/register").permitAll()
//                        .requestMatchers("/api/v1/session").permitAll()
//                        .requestMatchers("/progressMoniter/save").permitAll()
//                        .requestMatchers("/api/spestcnd/save").permitAll()
//                        .requestMatchers("/api/v1/auth/login").permitAll()
//                        .anyRequest().authenticated()
//                                .anyRequest().permitAll()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .build();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource(){
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH" , "DELETE", "OPTIONS"));
//        configuration.setAllowCredentials(true);
//        configuration.addAllowedHeader("*");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    // Added: Print a generated password at startup
//    @Bean
//    public CommandLineRunner printGeneratedPassword() {
//        return args -> {
//            String generatedPassword = "admin123"; // You can generate one dynamically
//            System.out.println("Generated Security Password: " + generatedPassword);
//        };
//    }
//
//    // Added: Create a default user so you can log in
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails user = User.withUsername("user")
//                .password(bCryptPasswordEncoder().encode("admin123")) // Change as needed
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//}


package com.example.SPSProjectBackend.security;

import org.springframework.boot.CommandLineRunner; // Added for printing the password at startup
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager; // Added for default user
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(Customizer.withDefaults())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/").permitAll()
//                        .requestMatchers("/api/v1/verify/").permitAll()
//                        .requestMatchers("/api/report/").permitAll()
//                        .requestMatchers("/api/application/").permitAll()
//                        .requestMatchers("/api/spestcnd/save").permitAll()
//                        .requestMatchers("/api/v1/auth/login").permitAll()
//                        .requestMatchers("/api/v1/register").permitAll()
//                        .requestMatchers("/api/commission/**").permitAll()
//                        //.requestMatchers("/api/commission/**").authenticated() // Add this line
//                        .requestMatchers("/api/pcesthmt/**").permitAll() // Keep existing
//                        .requestMatchers("/api/applicants/**").permitAll()
//                        .requestMatchers("/api/piv-details/**").permitAll()
//                        .requestMatchers("/api/wiring-land-details/**").permitAll()
//                        .requestMatchers("/api/estimate-details/**").permitAll()
//                        .requestMatchers("/api/approval-history/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults()) // enable global CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/**",
                                "/api/v1/verify/**",
                                "/api/report/**",
                                "/api/application/**",
                                "/api/spestcnd/**",
                                "/api/v1/auth/login",
                                "/api/v1/register",
                                "/api/commission/**",
                                "/api/pcesthmt/**",
                                "/api/applicants/**",
                                "/api/piv-details/**",
                                "/api/wiring-land-details/**",
                                "/api/estimate-details/**",
                                "/api/approval-history/**"
                        ).permitAll()
                        .anyRequest().authenticated() // all other endpoints secured
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8095"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH" , "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // Added: Print a generated password at startup
    @Bean
    public CommandLineRunner printGeneratedPassword() {
        return args -> {
            String generatedPassword = "admin123"; // You can generate one dynamically
            System.out.println("Generated Security Password: " + generatedPassword);
        };
    }

    // Added: Create a default user so you can log in
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withUsername("user")
                .password(bCryptPasswordEncoder().encode("admin123")) // Change as needed
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}