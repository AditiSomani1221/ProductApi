package com.example.starter.controllers;

import com.example.starter.entities.Users;
import com.example.starter.error.RoutingError;
import com.example.starter.handlers.FindUser;
import com.example.starter.response.Response;
import com.example.starter.response.ResponseUtils;
import com.example.starter.security.TokenService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Single;

import java.util.HashMap;
import java.util.Map;

public enum LoginController {
  INSTANCE;
  private static final Logger log = LoggerFactory.getLogger(LoginController.class);


  public void handle(RoutingContext context) {
    Single.just(context)
      .subscribeOn(RxHelper.blockingScheduler(context.vertx()))
      .map(this::doNext)
      .subscribe(
        response -> ResponseUtils.INSTANCE.writeJsonResponse(context, response),
        error -> {
          log.error("Error handling login", error);
          context.response().setStatusCode(500).end("Internal Server Error");
        }
      );
  }

  private Response doNext(RoutingContext context) {
    Response response = new Response();
    try {
      JsonObject bodyAsJson = context.getBodyAsJson();
      LoginRequest loginRequest = bodyAsJson.mapTo(LoginRequest.class);
      Users user = FindUser.INSTANCE.findUserByUsername(loginRequest.getUserName())
        .orElseThrow(() -> new RoutingError("User not found with given userName: " + loginRequest.getUserName()));

      if (!user.getPassword().equals(loginRequest.getPassword())) {
        throw new RoutingError("Invalid Password");
      }
      String token = TokenService.INSTANCE.generateToken(user.getMobileNo(), user.getId());
      Map<String, String> data = new HashMap<>();
      data.put("token", token);
      response.setMessage("success");
      response.setData(data);
    } catch (RoutingError e) {
      log.error("Routing error occurred", e);
      throw e;
    } catch (Exception e) {
      log.error("Unexpected error", e);
      throw new RuntimeException("Internal Server Error", e);
    }
    return response;
  }



  @JsonInclude(JsonInclude.Include.NON_NULL)
  private static class LoginRequest {
    @JsonProperty(value = "userName")
    private String userName;
    @JsonProperty(value = "password")
    private String password;

    public String getUserName() {
      return userName;
    }

    public String getPassword() {
      return password;
    }

    public LoginRequest() {}
  }
}
