package br.com.wmoddev.examplesecurity.config.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.wmoddev.examplesecurity.config.security.util.JwtTokenUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private final JwtTokenUtil jwtTokenUtil;
	private final JwtTokenService jwtTokenService;
	private final UserDetailsService userDetailsService;

	public JwtRequestFilter(final JwtTokenUtil jwtTokenUtil,
							final JwtTokenService jwtTokenService,
							final UserDetailsService userDetailsService) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.jwtTokenService = jwtTokenService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader(jwtTokenUtil.getAuthorizationHeader());
		
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtTokenUtil.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }
        
        final String token = authorizationHeader.replace(jwtTokenUtil.getTokenPrefix() + " ", "");
        final String username = jwtTokenService.validateTokenAndGetUsername(token);
        
        if (Strings.isNullOrEmpty(username)) {
        	filterChain.doFilter(request, response);
            return;
        }

        // set user details on spring security context
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // continue with authenticated user
        filterChain.doFilter(request, response);
		
	}

}
