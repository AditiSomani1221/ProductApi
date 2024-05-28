package com.example.starter.handlers;

import com.example.starter.entities.Product;
import com.example.starter.entities.Users;
import com.example.starter.response.Status;
import io.ebean.DB;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.Optional;

public enum FindUser {
INSTANCE;
  public Optional<Users> findUserByUsername(String username) {
    Users user = DB.find(Users.class)
      .where()
      .eq("username", username)
      .findOne();
    return Optional.ofNullable(user);
  }

  public Optional<Users> findUserById(Integer id) {
    Users user = DB.find(Users.class, id);
    return Optional.ofNullable(user);
  }
}
