package com.example.starter.handlers;

import com.example.starter.entities.Product;
import com.example.starter.request.UpdateProduct;
import com.example.starter.response.Status;
import io.ebean.DB;
import io.ebean.Database;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;

public enum UpdateProductHandler implements Handler<RoutingContext> {
  INSTANCE;

  @Override
  public void handle(RoutingContext event) {
    Database database = DB.getDefault();
    UpdateProduct updateProduct = event.body().asPojo(UpdateProduct.class);
    Product product = database.find(Product.class, updateProduct.getId());
    Optional<Product> optional = Optional.ofNullable(product);
    if(!optional.isPresent()){
      event.response()
        .setStatusCode(HttpResponseStatus.NOT_FOUND.code())
        .end(new Status(-1, "Product not available with " + updateProduct.getId() + " id").toJsonObject().toBuffer());
    }
    assert product != null;
    product.setName(updateProduct.getName());
    product.setQuantity(updateProduct.getQuantity());

    database.update(product);
    event.response().setStatusCode(HttpResponseStatus.OK.code())
      .end(new Status("Product updated successfully").toJsonObject().toBuffer());
  }

}
