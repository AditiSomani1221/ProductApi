package com.example.starter.handlers;

import com.example.starter.entities.Product;
import com.example.starter.request.CreateProductRequest;
import io.ebean.DB;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum  CreateProductHandler implements Handler<RoutingContext> {
  INSTANCE;

  public static final Logger LOG = LoggerFactory.getLogger(CreateProductHandler.class);

  @Override
  public void handle(RoutingContext context) {
    LOG.info("Handling create product request");
    CreateProductRequest createProductRequest = context.body().asPojo(CreateProductRequest.class);

    Product product = new Product();

    product.setName(createProductRequest.getName());
    product.setQuantity(createProductRequest.getQuantity());

    DB.save(product);

    context.response()
      .setStatusCode(200) // HTTP 200 OK
      .putHeader("Content-Type", "application/json") // Set the Content-Type header
      .end(new JsonObject()
        .put("code", 200)
        .put("message", "Success message")
        .encodePrettily()); // Convert JsonObject to a String and end the response


  }
}

