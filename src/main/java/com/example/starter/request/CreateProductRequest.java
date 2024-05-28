package com.example.starter.request;

public class CreateProductRequest {
  private String name;
  private Integer quantity;

  // Constructors
  public CreateProductRequest() {}

  public CreateProductRequest(String name, Integer quantity) {
    this.name = name;
    this.quantity = quantity;
  }

  // Getter and setter methods
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
