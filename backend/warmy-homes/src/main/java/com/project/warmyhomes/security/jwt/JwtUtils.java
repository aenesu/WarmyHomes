package com.project.warmyhomes.security.jwt;

import com.project.warmyhomes.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${backendapi.app.jwtExpirationMs}")
    private long jwtExpirations;

    @Value("${backendapi.app.jwtSecret}")
    private String jwtSecret;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return generateTokenFromEmail(userDetails.getEmail());
    }

    /**
     * @param jwtToken token to validate
     * @return true of JWT is correct otherwise will return FALSE.
     */
    public Boolean validateJwt(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
            return true;
        } catch (ExpiredJwtException e) {
            LOGGER.error("JWT token is expired : {} ", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JWT token is unsupported : {} ", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("JWT token is invalid : {} ", e.getMessage());
        } catch (SignatureException e) {
            LOGGER.error("JWT Signature is invalid : {} ", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT is empty : {} ", e.getMessage());
        }
        return false;
    }

    /**
     * @param email as String
     * @return JWT signed with algorithm and our jwtSecret key
     */
    public String generateTokenFromEmail(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirations))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
