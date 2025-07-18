import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Désactive CSRF
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Autorise toutes les requêtes
            )
            .httpBasic().disable() // Désactive l'authentification HTTP Basic
            .formLogin().disable(); // Désactive le formulaire de login

        return http.build();
    }
}
