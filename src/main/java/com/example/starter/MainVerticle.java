package com.example.starter;

import com.example.starter.configration.ProductConfigLoader;

import com.example.starter.request.ConfigManager;
import com.example.starter.request.ConfigUtil;
import io.vertx.core.*;
import com.example.starter.configration.ProductConfig;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class MainVerticle extends AbstractVerticle {

  public static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);

  public static final int numberOfInstances = Runtime.getRuntime().availableProcessors() / 2;

  public static void main(String[] args) {
    Vertx vertx1 = Vertx.vertx();
    vertx1.deployVerticle(MainVerticle.class.getName());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    ConfigManager.INSTANCE.setMainConfig(config());
    System.setProperty(ProductConfigLoader.SERVER_PORT, "4000");
    vertx.deployVerticle(HttpRoute.class.getName(), new DeploymentOptions().setInstances(numberOfInstances))
      .onFailure(startPromise::fail)
      .onSuccess(e -> {
        LOG.info(String.format("%s Deployed with id %s", HttpRoute.class.getName(), e));
        startPromise.complete();
      });
  }

}
