package com.example.starter.security;

import com.auth0.jwt.JWT;
import com.example.starter.error.RoutingError;
import com.example.starter.request.AccessToken;
import com.example.starter.request.ConfigUtil;
import com.example.starter.request.Request;
import com.auth0.jwt.algorithms.Algorithm;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import org.apache.commons.lang3.time.DateUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public enum ExternalApi {
  INSTANCE;
  public void authenticateRequest(RoutingContext routingContext)  throws  RoutingError {
    JsonObject body = routingContext.getBodyAsJson();
    Request request = body.mapTo(Request.class);
//    String bodyAsString = routingContext.getBodyAsString();
//    Request request1 = new Gson().fromJson(bodyAsString, Request.class);
    if(request.getUserName() == null) {
      System.out.println("Provide Username");
    }
    if (request.getPassword() == null) {
      System.out.println("Provide Password");
    }
    if (!request.getUserName().equals(ConfigUtil.INSTANCE.getAuthDetails().getString("username")) ||
      !request.getPassword().equals(ConfigUtil.INSTANCE.getAuthDetails().getString("password"))) {
      throw new RoutingError("Username or password did not match");
    }
    String token;

      Algorithm algorithm = Algorithm.HMAC256("secret");
      token = JWT.create()
        .withIssuer("your-issuer")
        .withExpiresAt(DateUtils.addMinutes(new Date(), 90))
        .withClaim("username", request.getUserName())
        .sign(algorithm);







  }

}

