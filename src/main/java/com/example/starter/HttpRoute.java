package com.example.starter;

import com.example.starter.controllers.Login;
import com.example.starter.ebean.EbeanConfig;
import com.example.starter.configration.ProductConfig;
import com.example.starter.configration.ProductConfigLoader;
import com.example.starter.controllers.ProductControllers;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class HttpRoute extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(HttpRoute.class);


   @Override
    public void start(Promise<Void> startPromise) throws Exception {
   Router router = Router.router(vertx);
   ProductConfigLoader.load(vertx)
       .onFailure(startPromise::fail)
        .onSuccess(
          configuration ->{
            LOG.info("Retrieved configuration : {} ");
            handleRoutes(startPromise,configuration, router);
          });
      router.route().handler(BodyHandler.create())
        .failureHandler(error -> {
          if(error.response().ended()){
            return;
          }
          LOG.error("Error: {}", error.failure());
          error.response()
            .setStatusCode(500)
            .end(new JsonObject().put("message", "Something went Wrong").toBuffer());
        });

    }

    public void handleRoutes(Promise<Void> startPromise, ProductConfig config, Router router){
      ProductControllers.INSTANCE.router(router);
     // Login.INSTANCE.router(router);

      router.get("/health").handler(event -> {
        event.response().end("OK");
      });

      vertx.createHttpServer().requestHandler(router)
        .exceptionHandler(error -> {
          LOG.error("HTTP server error: ", error.getCause());
        })
        .listen(config.getServerPort(), http -> {
          if(http.succeeded()){
            startPromise.complete();
            EbeanConfig.configureDatabase();
            LOG.debug(String.format("HTTP server started on port %s", config.getServerPort()));
          }else startPromise.fail(http.cause());
        });
    }
//  public class HttpRoute extends AbstractVerticle {
//
//    private static final Logger logger = LoggerFactory.getLogger(HttpRoute.class);
//    public HttpServer httpServer;
//    @Override
//    public void start(io.vertx.core.Promise<Void> startPromise) throws Exception{
//      Router router=Router.router((Vertx) vertx);
//      router.route().handler(BodyHandler.create());
//      HttpServerOptions serverOptions=new HttpServerOptions();
//      serverOptions.setCompressionSupported(true);
//      httpServer=vertx
//        .createHttpServer(serverOptions)
//        .requestHandler(router)
//        .listen(ConfigManager.INSTANCE.getMainConfig().getInteger("port"), httpListenHandler -> {
//          if (httpListenHandler.succeeded()) {
//            logger.info("HTTP server started ");
//            startPromise.complete();
//          } else {
//            logger.info("HTTP server failed to start : {}", httpListenHandler.cause());
//            startPromise.fail(httpListenHandler.cause().getMessage());
//          }
//        });
//      try {
//        Login.INSTANCE.router(router);
//      } catch (Exception e ) {
//        logger.error("Exception : {}", e);
//      }
//    }
  }
