package net.techmentor.cases_service.shared.auth;

import net.techmentor.cases_service.shared.exceptions.UnAuthorized;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtVerifier {
    private final String secretKey;

    public JwtVerifier(@Value("${auth.secretKey}") String secretKey) {
        this.secretKey = secretKey;
    }

    public Claims verify(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        validateExpiry(claims);
        return claims;
    }

    private SecretKey secretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private void validateExpiry(Claims claims) {
        if (!claims.getExpiration().after(new Date())) {
            throw new UnAuthorized("JWT token expired");
        }
    }
}
