package com.example.starter.configration;

import io.vertx.core.json.JsonObject;



import io.vertx.core.json.JsonObject;

public class ProductConfig {

  int serverPort;

  String version;

  public static ProductConfig from(JsonObject jsonObject){
    return new ProductConfig()
      .setServerPort(jsonObject.getInteger("SERVER_PORT"))
      .setVersion(jsonObject.getString("version"))
      ;
  }

  public int getServerPort() {
    return serverPort;
  }

  public ProductConfig setServerPort(int serverPort) {
    this.serverPort = serverPort;
    return this;
  }

  public String getVersion() {
    return version;
  }

  public ProductConfig setVersion(String version) {
    this.version = version;
    return this;
  }
}
