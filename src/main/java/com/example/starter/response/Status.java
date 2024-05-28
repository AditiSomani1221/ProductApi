package com.example.starter.response;

import io.vertx.core.json.JsonObject;

public class Status {
  public int code = 0;
  public String message;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Status(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public Status(String message) {
    this.message = message;
  }

  public JsonObject toJsonObject(){
    return JsonObject.mapFrom(this);
  }
}


