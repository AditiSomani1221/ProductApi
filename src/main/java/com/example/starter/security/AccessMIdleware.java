package com.example.starter.security;


import com.example.starter.entities.Users;
import com.example.starter.error.RoutingError;
import com.example.starter.handlers.FindUser;
import com.example.starter.request.LoginRequest;
import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.ext.web.RoutingContext;

import rx.Single;
;

public enum AccessMIdleware {
  INSTANCE;

  public Single<LoginRequest> authenticateRequest(RoutingContext context) {
    return Single.just(context).subscribeOn(RxHelper.blockingScheduler(context.vertx()))
      .map(this::validateRequest);
  }

  private LoginRequest validateRequest(RoutingContext context) {
    String token = context.request().getHeader("Authorization");

    if (token==null) {
      throw new RoutingError("Token Not Found in the header");
    }
    if (token.length()<7) {
      throw new RoutingError("Invalid Token");
    }
    String tokenType = token.substring(0, 6);

    if (!tokenType.equals("Bearer")) {
      throw new RoutingError("Invalid Token Type");
    }
    String accessToken = token.substring(7);
    Integer userId = TokenService.INSTANCE.getIdFromToken(accessToken);
    if (userId==null) {
      throw new RoutingError("Invalid Token");
    }

    Users users = FindUser.INSTANCE.findUserById(userId)
      .orElseThrow(() -> new RoutingError("User Not Found"));
    LoginRequest request = new LoginRequest();
    request.setContext(context);
    request.setUsers(users);
    return request;
  }

}
