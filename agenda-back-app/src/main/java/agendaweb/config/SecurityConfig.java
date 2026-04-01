package agendaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
/*
        http.cors(cors -> cors.configurationSource(request -> {
                    // Return your specific CorsConfiguration here,
                    // or leverage the global WebMvcConfigurer bean
                    org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
                    config.addAllowedOrigin("http://localhost:4200");
                    config.addAllowedMethod("*"); // Allow all methods
                    config.addAllowedHeader("*"); // Allow all headers
                    config.setAllowCredentials(true); // Allow credentials if your client sends them
                    config.setMaxAge(3600L); // Set maxAge for preflight requests
                    // ... other configurations
                    return config;
             }))
*/
        http.csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated();
        System.out.println("/////////////// SecurityFilterChain ///////////////////");

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Configure CORS settings
        config.addAllowedOrigin("http://localhost:4200"); // Allow specific origin
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("*"); // Allow specific methods
        config.setAllowCredentials(true); // Allow credentials (e.g., cookies)
        config.setMaxAge(3600L); // Cache preflight requests for 1 hour
        source.registerCorsConfiguration("/**", config); // Apply to all endpoints

        System.out.println("/////////////// CorsConfigurationSource ///////////////////");

        return source;
    }
}
