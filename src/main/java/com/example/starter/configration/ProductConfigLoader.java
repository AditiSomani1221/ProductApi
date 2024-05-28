package com.example.starter.configration;




import io.ebeaninternal.server.lib.Str;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;




public class ProductConfigLoader {

  private static final Logger LOG = LoggerFactory.getLogger(ProductConfigLoader.class);

  public static String SERVER_PORT= "SERVER_PORT";
  static final List<String> EXPOSED_ENVIRONMENT_VARIABLES = new ArrayList<>(Collections.singletonList(SERVER_PORT));
  public static Future<ProductConfig> load(Vertx vertx){

    final JsonArray array = new JsonArray();
    EXPOSED_ENVIRONMENT_VARIABLES.forEach(s -> {
      LOG.debug("Fetch configuration for {}", array.encode());
      array.add(s);
    });

    ConfigStoreOptions configStoreOptions = new ConfigStoreOptions()
      .setType("env")
      .setConfig(new JsonObject().put("keys", array));

    ConfigStoreOptions propertyStore = new ConfigStoreOptions()
      .setType("sys")
      .setConfig(new JsonObject().put("cache", false));

    ConfigStoreOptions yamlStore = new ConfigStoreOptions()
      .setType("file")
      .setFormat("yaml")
      .setConfig(new JsonObject().put("path", "application.yml"));

    ConfigRetriever configRetriever = ConfigRetriever.create(vertx, new ConfigRetrieverOptions()
      .addStore(yamlStore)
      .addStore(configStoreOptions)
      .addStore(propertyStore));
    return configRetriever.getConfig().map(ProductConfig::from);
  }
}
