package com.example.starter.request;

import lombok.Data;
import lombok.Value;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import io.vertx.core.json.JsonObject;

import java.io.InputStream;

@Data
public enum ConfigUtil {
  INSTANCE;

  public JsonObject getAuthDetails() {
    return ConfigManager.INSTANCE.getMainConfig().getJsonObject("auth");
  }


}
