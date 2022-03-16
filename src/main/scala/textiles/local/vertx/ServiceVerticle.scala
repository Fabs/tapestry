package com.tapestry
package textiles.local.vertx

import framework.primitives.Service

import io.vertx.core.eventbus.Message
import io.vertx.core.{AbstractVerticle, Handler, Promise}

class ServiceVerticle[I, O, P](service: Service[I, O, P]) extends AbstractVerticle() {
  override def start(startPromise: Promise[Void]): Unit = {
    vertx.eventBus().consumer(service.address().uri, (event: Message[Any]) => {
      println(event.body())
    })
    startPromise.complete()
  }

  override def stop(stopPromise: Promise[Void]): Unit = {
    stopPromise.complete()
  }
}
