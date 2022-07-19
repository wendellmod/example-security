package br.com.wmoddev.examplesecurity.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.and()
				.authorizeHttpRequests()
				.anyRequest().authenticated()
				.and()
				.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("wendell")
			.password(passwordEncoder.encode("$teste_123"))
			.roles("ADMIN");
	}

}
