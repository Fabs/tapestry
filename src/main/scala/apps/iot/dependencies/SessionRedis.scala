package com.tapestry
package apps.iot.dependencies

import apps.iot.runtime.{ContainerServiceClient, ContainerServiceProps}
import framework.primitives.{BuilderContext, ExecutionContext, Service, ServiceCompute}

class SessionRedis extends Service[Unit, Unit, ContainerServiceProps] {
  override def execute(in: Unit)(implicit context: ExecutionContext): Unit = ???

  override def configure()(implicit builder: BuilderContext): ServiceCompute[Unit, Unit, ContainerServiceProps] = {
    new ContainerServiceClient(builder, this.getClass.getName, this,
      ContainerServiceProps("redis", "latest", "redis-server", List("6379:6379"))
    )
  }
}
