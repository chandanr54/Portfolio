package com.project.portfolio.security;


import com.project.portfolio.commanutil.MyMessages;
import com.project.portfolio.commanutil.Role;
import com.project.portfolio.exception.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final Logger logger= LoggerFactory.getLogger(SecurityConfiguration.class);

    private  final JwtAuthenticationsFilters jwtAuthenticationsFilters;
    private final CustomAccessDeniedHandler  customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.debug("securityFilterChain");
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                                    authorizeRequests
                                            .requestMatchers(MyMessages.ADMIN_URL+"/**").hasRole("ADMIN")
                                            .requestMatchers(MyMessages.USER_URL+"/**").hasRole("USER")
                                            .requestMatchers(MyMessages.AUTH_URL+"/**").permitAll()
                                            .anyRequest().authenticated())
                .exceptionHandling(exceptionHandler ->exceptionHandler.
                            accessDeniedHandler(customAccessDeniedHandler));

        http.addFilterBefore(jwtAuthenticationsFilters, UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }





      @Bean
    public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
      }
}
