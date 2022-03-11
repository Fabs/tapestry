package com.tapestry
package apps.iot.service

import apps.latac.runtime.Lambda
import framework.primitives.{BuilderContext, ExecutionContext, Service, Construct}

class HelloService extends Service[Unit, String, Unit]{
  override def execute(in: Unit)(implicit context: ExecutionContext): String = {
    println("Hello Ukraine")

    "Hello Ukraine"
  }

  override def configure()(implicit builder: BuilderContext): Construct[Unit, String, Unit] = {
    new Lambda[Unit, String](builder, this.getClass.getName, this)
  }
}
