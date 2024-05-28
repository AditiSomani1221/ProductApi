package com.example.starter.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.starter.error.RoutingError;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public enum TokenService {
  INSTANCE;
  String secret = "qwertyuioooooooopasdfghjklaxcvbnm";


  public String generateToken(String userName,Integer id) {
    String token = null;
    try {
      token = JWT.create()
        .withExpiresAt(Date.from(Instant.now().plus(10, ChronoUnit.MINUTES)))
        .withClaim("userName", userName)
        .withClaim("id", id)
        .sign(Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return token;
  }

  public DecodedJWT decodeToken(String token) throws RoutingError {
    JWTVerifier verifier=null;
    verifier =JWT.require(Algorithm.HMAC256(secret.getBytes())).build();
    DecodedJWT decodedJWT=null;
    try {
      decodedJWT = verifier.verify(token);
    } catch (JWTVerificationException e) {
      throw new RoutingError("Token is expired!");
    }
    return decodedJWT;
  }
  public Integer getIdFromToken(String accessToken) {
    return decodeToken(accessToken).getClaim("id").asInt();
  }

  public String getUserNameFromToken(String accessToken) throws RoutingError {
    return decodeToken(accessToken).getClaim("userName").asString();
  }
}
