package com.example.starter.request;

import io.ebean.annotation.Expose;

public class UpdateProduct {
@Expose
  private Integer id;
@Expose
  private  String name;
@Expose
  private  Integer quantity;

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getQuantity() {
    return quantity;
  }
}
