package com.example.starter.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response {
  private Object data;
  private String message;
  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }


  private List<String> errors;
  public List<String> getErrors() {
    if (errors==null) {
      errors=new ArrayList<>();
    }
    return errors;
  }
  public Response() {}
  public Response(String message) {
    this.getErrors().add(message);
    this.message = message;
  }
}

