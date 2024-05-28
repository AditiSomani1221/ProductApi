package com.example.starter.response;




import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
public enum ResponseUtils {
  INSTANCE;
  private static final Logger logger = LoggerFactory.getLogger(ResponseUtils.class);
  public void writeJsonResponse(RoutingContext context, Object object) {
    try {
      context.response()
        .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
        .end(JsonObject.mapFrom(object).put("status", "success").encode());
    } catch (Exception e) {
      logger.error("Exception in writeJsonResponse : {}", e);
    }
  }
  public void writeJsonResponse(io.vertx.rxjava.ext.web.RoutingContext context, Object object) {
    try {
      context.response()
        .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
        .end(JsonObject.mapFrom(object).put("status", "success").encode());
    } catch (Exception e) {
      logger.error("Exception in writeJsonResponse : {}", e);
    }
  }
  public void writeJsonResponse(RoutingContext context, Object object, String status) {
    try {
      context.response()
        .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
        .end(JsonObject.mapFrom(object).put("status", status).encode());
    } catch (Exception e) {
      logger.error("Exception in writeJsonResponse : {}", e);
    }
  }
  public void writeJsonResponse(RoutingContext context, Object object, String status, int statusCode) {
    try {
      context.response()
        .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
        .end(JsonObject.mapFrom(object).put("status", status).encode());
    } catch (Exception e) {
      logger.error("Exception in converting Response in json : {}", e);
    }
  }

  public void writeJsonErrorResponse(RoutingContext context, String status, int statusCode) {
    try {
      context.response()
        .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
        .setStatusCode(statusCode)
        .end(new JsonObject().put("status", status).put("statusCode", statusCode).encode());
    } catch (Exception e) {
      logger.error("Exception in writeJsonErrorResponse : {}", e);
    }
  }

  public void writeJsonErrorResponse(RoutingContext context, Object data, int statusCode) {
    try {
      context.response()
        .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
        .setStatusCode(statusCode)
        .end(JsonObject.mapFrom(data).put("status", "fail").encode());
    } catch (Exception e) {
      logger.error("Exception occurred while mapping error response : {}", e);
    }
  }
  public void writeJsonErrorResponse(io.vertx.rxjava.ext.web.RoutingContext context, Object data, int statusCode) {
    logger.error("error Message : {}", data);
    try {
      if (!context.response().closed()) {
        context.response()
          .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
          .setStatusCode(statusCode)
          .end(JsonObject.mapFrom(data).put("status", "fail").encode());
      }
    } catch (Exception e) {
      logger.error("Exception occurred while mapping error response : {}", e);
    }
  }

}
