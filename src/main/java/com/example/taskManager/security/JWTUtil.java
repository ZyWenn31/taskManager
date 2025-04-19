package com.example.taskManager.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    private final String secret = "sasha";

    public String generateToken(String email){
        Date experationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withIssuer("spring")
                .withExpiresAt(experationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
         JWTVerifier jwtVerifier =  JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("spring")
                .build();

         DecodedJWT jwt = jwtVerifier.verify(token);

         return jwt.getClaim("email").asString();
    }
}
