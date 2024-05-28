package com.example.starter.response;

import com.example.starter.entities.Product;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {
  private Product product;

  private Status status;

  public Product getProduct() {
    return product;
  }

  public ProductResponse setProduct(Product product) {
    this.product = product;
    return this;
  }

  public Status getStatus() {
    return status;
  }

  public ProductResponse setStatus(Status status) {
    this.status = status;
    return this;
  }

  public JsonObject toJsonObject(){
    return JsonObject.mapFrom(this);
  }

}
