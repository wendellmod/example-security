package br.com.wmoddev.examplesecurity.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.com.wmoddev.examplesecurity.config.security.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	
	private final JwtRequestFilter jwtRequestFilter;

	public WebSecurityConfig(final JwtRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}

	private static final String[] PUBLIC_MATCHERS_POST = { "/users", "/auth/authenticate" };
	
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.cors().and()
				.csrf().disable()
				.authorizeHttpRequests()
				.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
				.anyRequest().authenticated()
				.and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
    
	@Bean
    CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.OPTIONS);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
}
