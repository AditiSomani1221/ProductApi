package com.example.starter.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class Request {
  private String userName;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  private String password;


}
