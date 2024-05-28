package com.example.starter.controllers;

import com.example.starter.handlers.UpdateProductHandler;
import io.vertx.rxjava.ext.web.Router;

public enum Login {
  INSTANCE;
  public void router(Router router) {
    router.post("/login").handler(LoginController.INSTANCE::handle);
  }
}
