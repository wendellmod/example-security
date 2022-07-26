package br.com.wmoddev.examplesecurity.config.security.util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class JwtTokenUtil {
	
    @Value("${jwt.token-prefix}")
    private String tokenPrefix;
	
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-minutes}")
    private int expireInMinutes;
    
    public String getTokenPrefix() {
		return tokenPrefix + " ";
	}

	public String getSecret() {
		return secret;
	}

	public int getExpireInMinutes() {
		return expireInMinutes;
	}

	public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }

    public Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(OffsetDateTime.now().getOffset()));
    }
    
	public String getTokenFromRequest(HttpServletRequest request) {
		return request.getHeader(getAuthorizationHeader());
	}
	
}
