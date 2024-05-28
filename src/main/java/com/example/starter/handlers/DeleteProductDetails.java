package com.example.starter.handlers;

import com.example.starter.entities.Product;
import com.example.starter.request.CreateProductRequest;
import com.example.starter.response.Status;
import io.ebean.DB;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


public enum DeleteProductDetails implements Handler<RoutingContext> {
  INSTANCE;

  private static final Logger LOG = LoggerFactory.getLogger(DeleteProductDetails.class);

  @Override
  public void handle(RoutingContext event) {
    Integer id = Integer.valueOf(event.request().getParam("id"));
    try {
      Product product = DB.find(Product.class)
        .where()
        .eq("id", id)
        .findOne();

      if (product == null) {
        LOG.info("Product not found with id: {}", id);
        event.response()
          .setStatusCode(HttpResponseStatus.NOT_FOUND.code())
          .end(new Status(-3, "Product not found with id: " + id).toJsonObject().toBuffer());
        return;
      }

      DB.delete(product);
      LOG.info("Product deleted successfully with id: {}", id);
      event.response().setStatusCode(HttpResponseStatus.OK.code())
        .end(new Status("Product deleted successfully with id: " + id).toJsonObject().toBuffer());

    } catch (Exception e) {
      LOG.error("Error deleting product with id: {}", id, e);
      event.response()
        .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
        .end(new JsonObject().put("message", "Error deleting product").toBuffer());
    }
  }
}
