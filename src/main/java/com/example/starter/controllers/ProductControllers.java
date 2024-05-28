package com.example.starter.controllers;

import com.example.starter.handlers.*;

import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.Router;



  public enum ProductControllers {
    INSTANCE;
    public static final Logger LOG = LoggerFactory.getLogger(ProductControllers.class);

    public void router(Router router){
      router.post("/product").handler(CreateProductHandler.INSTANCE::handle);
      router.get("/product/:id").handler(GetProductHandler.INSTANCE);
      router.delete("/product/:id").handler(DeleteProductDetails.INSTANCE);
      router.put("/product").handler(UpdateProductHandler.INSTANCE);
      router.patch("/product").handler(UpdateProductHandler.INSTANCE);
      router.delete("/productsoft/:id").handler(SoftDeleteProductHandler.INSTANCE);

      router.get("/products").handler(GetPoductsHandler.INSTANCE);
    }


}
