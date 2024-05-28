package com.example.starter.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Table(name ="users")
@Data
public class Users {

  private Integer id;
  private String userName;
  private String mobileNo;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  private String password;



}
