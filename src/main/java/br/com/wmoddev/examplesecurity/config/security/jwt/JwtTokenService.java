package br.com.wmoddev.examplesecurity.config.security.jwt;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.wmoddev.examplesecurity.config.security.util.JwtTokenUtil;

@Service
public class JwtTokenService {
	
	private final JwtTokenUtil jwtTokenUtil;

    public JwtTokenService(final JwtTokenUtil jwtTokenUtil) {
    	this.jwtTokenUtil = jwtTokenUtil;
    }
	
	public String generateToken(final UserDetails userDetails) {
      Date issuedAt = jwtTokenUtil.getDateFromLocalDateTime(LocalDateTime.now());
      Date expiresAt = jwtTokenUtil.getDateFromLocalDateTime(LocalDateTime.now().plusMinutes(jwtTokenUtil.getExpireInMinutes()));
      String[] arrayClain = getArrayClainAuthorities(userDetails.getAuthorities());
      
      return JWT.create()
      			.withSubject(userDetails.getUsername())
      			.withArrayClaim("authorities", arrayClain)
      			.withIssuedAt(issuedAt)
      			.withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC512(jwtTokenUtil.getSecret()));
    }

    public String validateTokenAndGetUsername(final String token) {
        try {
            DecodedJWT decoded = decodedJwt(token);
            return decoded.getSubject();
        } catch (final JWTVerificationException verificationEx) {
            //log.warn("token invalid: {}", verificationEx.getMessage());
            return null;
        }
    }
    
    private DecodedJWT decodedJwt(final String token) {
    	return JWT.require(Algorithm.HMAC512(jwtTokenUtil.getSecret()))
    				.build()
    				.verify(token);
    }
    
    private String[] getArrayClainAuthorities(Collection<? extends GrantedAuthority> authorities) {
    	List<String> toListString = authorities.stream().map(m -> m.getAuthority()).collect(Collectors.toList());

    	return (String[]) toListString.toArray(new String[0]);
    }

}
