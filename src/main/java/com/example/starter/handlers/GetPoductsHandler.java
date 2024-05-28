package com.example.starter.handlers;

import com.example.starter.controllers.ProductControllers;
import com.example.starter.entities.Product;
import com.example.starter.response.ProductsResponse;
import com.example.starter.response.Status;
import com.example.starter.security.ExternalApi;
import io.ebean.DB;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.List;

public enum GetPoductsHandler implements Handler<RoutingContext> {

  INSTANCE;

  public static final Logger LOG = LoggerFactory.getLogger(ProductControllers.class);

  @Override
  public void handle(RoutingContext event) {
    LOG.info("Handling get products request");
    Observable.fromCallable(() -> {
        ExternalApi.INSTANCE.authenticateRequest(event);
        return true;
      })
      .subscribe(
        result -> {
          System.out.println(" successful");
        },
        error -> {
          System.out.println("Username and password did not match");
        }
      );


    List<Product> list = DB.find(Product.class)
      // Ensure your query does not implicitly include non-existent columns
      .where()
      //.eq("is_deleted", false) // Uncomment and modify this line if you have an is_deleted column
      .findList();

    LOG.info("List of products: {}", list);

    if (list.isEmpty()) {
      event.response()
        .setStatusCode(HttpResponseStatus.NOT_FOUND.code())
        .end(new JsonObject().put("message", "Products not found").toBuffer());
      return;
    }

    ProductsResponse productsResponse = new ProductsResponse();
    productsResponse.setProductsList(list);
    productsResponse.setStatus(new Status("Products retrieved successfully"));

    event.response()
      .setStatusCode(HttpResponseStatus.OK.code())
      .putHeader("Content-Type", "application/json")
      .end(JsonObject.mapFrom(productsResponse).encode());
  }
}
