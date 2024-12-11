package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Entry point for the Backend Spring Boot application.
 *
 * This class initializes the Spring Boot application and configures
 * Cross-Origin Resource Sharing (CORS) settings to allow requests
 * from the frontend application.
 */
@SpringBootApplication
public class BackendApplication {

    /**
     * Main method to run the Spring Boot application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    /**
     * Configures CORS settings for the application.
     *
     * This configuration allows the frontend application running at
     * "http://localhost:3000" to communicate with the backend.
     *
     * @return a {@link WebMvcConfigurer} bean to configure CORS mappings
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            /**
             * Adds CORS mappings to allow specific origins, methods, and headers.
             *
             * @param registry the {@link CorsRegistry} used to define CORS rules
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply to all endpoints
                        .allowedOrigins("http://localhost:3000") // Allow requests from your React app
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify HTTP methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Allow cookies and credentials
            }
        };
    }
}
