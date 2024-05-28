package com.example.starter.handlers;

import com.example.starter.entities.Product;
import com.example.starter.response.ProductResponse;
import com.example.starter.response.Status;
import io.ebean.DB;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.util.Optional;


public enum GetProductHandler implements Handler<RoutingContext> {
  INSTANCE;

  @Override
  public void handle(RoutingContext event) {

    Integer id;
    try {
      id = Integer.valueOf(event.request().getParam("id"));
    } catch (NumberFormatException e) {
      event.response()
        .setStatusCode(400) // HTTP 400 Bad Request
        .end(Json.encode(new Status(-3, "Invalid ID format")));
      return;
    }


    Product product = DB.find(Product.class, id);

    if (product == null) {
      event.response()
        .setStatusCode(404) // HTTP 404 Not Found
        .end(Json.encode(new Status(-2, "Product not found with respect to id: " + id)));
      return;
    }

    // Assuming ProductResponse can correctly serialize the product to JSON
    ProductResponse productResponse = new ProductResponse();
    productResponse.setProduct(product);
    productResponse.setStatus(new Status("Product retrieved successfully with id " + id));

    event.response()
      .setStatusCode(200) // HTTP 200 OK
      .putHeader("Content-Type", "application/json") // Set response content type
      .end(Json.encode(productResponse));
  }
}
