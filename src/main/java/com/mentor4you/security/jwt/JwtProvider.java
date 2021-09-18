package com.mentor4you.security.jwt;

import com.mentor4you.exception.InvalidTokenRequestException;
import com.mentor4you.model.Role;
import com.mentor4you.security.jwt.cache.event.OnUserLogoutSuccessEvent;
import com.mentor4you.security.jwt.cache.TokenCache;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Autowired
    private TokenCache tokenCache;

    @Value("$(jwt.secret)")
    private String jwtSecret;

    private String header="Authorization";

    public String generateToken(String login, Role role) {
        Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("role",role);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateAuthToken(Authentication authentication) {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expireDate = new Date(now.getTime()+604800);

        return Jwts.builder()
                .setSubject(customUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            validateTokenIsNotForALoggedOutDevice(authToken);
            return true;
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
           logger.error(e.getMessage());
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(header);
    }

    public Date getExpireDateFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration();
    }

    private void validateTokenIsNotForALoggedOutDevice(String authToken) {
        OnUserLogoutSuccessEvent previouslyLoggedOutEvent = tokenCache.getLogoutEventForToken(authToken);
        if (previouslyLoggedOutEvent != null) {
                String userEmail = previouslyLoggedOutEvent.getEmail();
                Date logoutEventDate = previouslyLoggedOutEvent.getTime();
                String errorMessage = String.format("Token corresponds to an already logged out user [%s] at [%s]. Please login again", userEmail, logoutEventDate);
                throw new InvalidTokenRequestException("JWT", authToken, errorMessage);
        }
    }

}