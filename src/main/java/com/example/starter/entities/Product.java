package com.example.starter.entities;

import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class  Product extends Model {
  @Id
  @Column(name = "product_id")
  private  Integer id;
  @Column(name = "product_name")
  private String name;
  @Column(name = "quantity")
  private  Integer Quantity;

  private boolean isDeleted;


  public boolean getIsDeleted() {
    return isDeleted;
  }

  public   void setIsDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getQuantity() {
    return Quantity;
  }

  public void setQuantity(Integer quantity) {
    Quantity = quantity;
  }
}
