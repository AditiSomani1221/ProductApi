package com.example.starter.handlers;

import com.example.starter.entities.Product;
import com.example.starter.response.Status;
import io.ebean.DB;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

import java.util.Optional;

public enum SoftDeleteProductHandler implements Handler<RoutingContext> {

  INSTANCE;

  @Override
  public void handle(RoutingContext event) {
  Integer id = Integer.valueOf(event.request().getParam("id"));
  Product employee = DB.find(Product.class)
    .where()
    .eq("product_id", id)
    .eq("is_Deleted", false).findOne();

  Optional<Product> optional = Optional.ofNullable(employee);
    if(!optional.isPresent()){
    event.response()
      .setStatusCode(HttpResponseStatus.NOT_FOUND.code())
      .end(new Status("Product not available with id : " + id).toJsonObject().toBuffer());
  }
  Product product = optional.get();
    product.setIsDeleted(true);
    DB.update(product);
    event.response()
      .setStatusCode(HttpResponseStatus.OK.code())
    .end(new Status("product with id : " + id  + " deleted sucessfully").toJsonObject().toBuffer());

  }
}


