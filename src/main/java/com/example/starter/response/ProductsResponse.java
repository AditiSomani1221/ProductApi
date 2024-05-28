package com.example.starter.response;

import com.example.starter.entities.Product;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class ProductsResponse {
  private List<Product> productsList = new ArrayList<>();

  private Status status;

  public ProductsResponse setProductsList(List<Product> productsList) {
    this.productsList = productsList;
    return this;
  }

  public ProductsResponse setStatus(Status status) {
    this.status = status;
    return this;
  }

  public JsonObject toJsonObject(){
    return JsonObject.mapFrom(this);
  }

  public List<Product> getProductsList() {
    return productsList;
  }

  public Status getStatus() {
    return status;
  }
}


