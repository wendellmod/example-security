package br.com.wmoddev.examplesecurity.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	private static final String[] PUBLIC_MATCHERS_POST = { "/users" }; 

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.and()
				.authorizeHttpRequests()
				.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
//				.antMatchers(HttpMethod.GET, "/users/**").hasRole("USER")
//				.antMatchers(HttpMethod.PUT, "/users/**").hasRole("USER")
//				.antMatchers(HttpMethod.PATCH, "/users/**").hasAnyRole("USER", "ADMIN")
//				.antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.csrf().disable();
		
		return http.build();
	}

}
