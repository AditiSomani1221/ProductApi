package com.example.starter.request;

import com.example.starter.entities.Users;

import lombok.Data;
import io.vertx.rxjava.ext.web.RoutingContext;


@Data
public class LoginRequest {
  private RoutingContext context;
  private Users users;
  public Users getUsers() {
    return users;
  }

  public void setUsers(Users users) {
    this.users = users;
  }


  public RoutingContext getContext() {
    return context;
  }

  public void setContext(RoutingContext context) {
    this.context = context;
  }



}
