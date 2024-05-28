package com.example.starter.ebean;

import com.example.starter.entities.Product;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;

import java.util.Properties;


  public class EbeanConfig {
    public static void configureDatabase(){

      DatabaseConfig config = new DatabaseConfig();
      Properties properties = new Properties();
      properties.put("datasource.db.username", "root");
      properties.put("datasource.db.password", "root");
      properties.put("datasource.db.databaseUrl", "jdbc:mysql://localhost:3306/product_details");
      properties.put("datasource.db.databaseDriver", "com.mysql.cj.jdbc.Driver");

      config.setDefaultServer(true);
      config.setDdlCreateOnly(true);
      config.setDdlGenerate(true);
      config.setDdlRun(true);
      config.loadFromProperties(properties);
      config.addClass(Product.class);

      DatabaseFactory.create(config);
    }
  }


