package br.com.faitec.fala_cidade.configuration;

import br.com.faitec.fala_cidade.implementation.dao.postgres.ReportPostgresDaoImpl;
import br.com.faitec.fala_cidade.implementation.dao.postgres.UserPostgresDaoImpl;
import br.com.faitec.fala_cidade.implementation.service.authentication.BasicAuthenticationServiceImpl;
import br.com.faitec.fala_cidade.implementation.service.authentication.JwtAuthenticationServiceImpl;
import br.com.faitec.fala_cidade.port.dao.report.ReportDao;
import br.com.faitec.fala_cidade.port.service.user.UserService;
import br.com.faitec.fala_cidade.port.dao.user.UserDao;
import br.com.faitec.fala_cidade.port.service.authentication.AuthenticationService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Connection;
import java.util.Arrays;

@Configuration
public class AppConfiguration {
    private final Environment environment;

    public AppConfiguration(Environment environment) {
        this.environment = environment;

        System.out.println("------------");
        System.out.println(Arrays.toString(environment.getActiveProfiles()));
        System.out.println("------------");
    }

    @Bean
    public UserDao getUserFakeDao(Connection connection){
        return new UserPostgresDaoImpl(connection);
    }

    @Bean
    public ReportDao getReportDao(Connection connection){
        return new ReportPostgresDaoImpl(connection);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET","POST","PUT","DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }

        };
    }

    @Bean
    public OpenAPI customOpenApi(){
        /**
         * Para acessar a URL do swagger pelo navegador, basta
         * digitar localhost:8080/swagger-ui.html
         * Lembrando que : 8080 é a porta, caso você tenha alterado no
         * arquivo application.properties, altere aqui também.
         * @retun;
         */
        return new OpenAPI().info(new Info().title("FAI LDS").version("0.0.1").description("API - FAI LDS"));
    }


    @Bean
    @Profile("basic")
    public AuthenticationService basicAuthenticationService(final UserService userService){
        return new BasicAuthenticationServiceImpl(userService);
    }

    @Bean
    @Profile("jwt")
    public AuthenticationService jwtAuthenticationService(final UserService userService, final PasswordEncoder passwordEncoder){
        return new JwtAuthenticationServiceImpl(userService, passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
