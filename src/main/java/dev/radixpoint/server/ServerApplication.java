package dev.radixpoint.server;

import dev.radixpoint.server.enumeration.Status;
import dev.radixpoint.server.model.Server;
import dev.radixpoint.server.repository.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static dev.radixpoint.server.enumeration.Status.SERVER_DOWN;
import static dev.radixpoint.server.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ServerRepo serverRepo) { //Everything here will run after app initializes
        return args -> {
            serverRepo.save(new Server(null, "71.163.168.152", "Ubuntu Linux", "16 GB", "Personal PC",
                    "http://localhost:8080/api/v1/server/image/server1.png", SERVER_UP));

            serverRepo.save(new Server(null, "192.168.1.58", "Fedora Linux", "32 GB", "Dell PC",
                    "http://localhost:8080/api/v1/server/image/server2.png", SERVER_DOWN));

            serverRepo.save(new Server(null, "192.168.1.21", "Windows 11", "64 GB", "Web Server",
                    "http://localhost:8080/api/v1/server/image/server3.png", SERVER_UP));

            serverRepo.save(new Server(null, "192.168.1.14", "Enterprise Linux", "64 GB", "Mail Server",
                    "http://localhost:8080/api/v1/server/image/server4.png", SERVER_DOWN));
        };
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
