package com.example.starter.error;

public class RoutingError extends RuntimeException{
  private int statusCode;

  public RoutingError() {
    super();
  }

  public RoutingError(String message) {
    super(message);

    this.statusCode = 500;
  }
}
