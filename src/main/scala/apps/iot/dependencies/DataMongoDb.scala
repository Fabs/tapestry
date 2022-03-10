package com.tapestry
package apps.iot.dependencies

import apps.iot.runtime.{ContainerServiceClient, ContainerServiceProps}
import framework.primitives.{BuilderContext, ExecutionContext, Service, ServiceCompute}

class DataMongoDb extends Service[Unit, Unit, ContainerServiceProps] {
  override def execute(in: Unit)(implicit context: ExecutionContext): Unit = ???

  override def configure()(implicit builder: BuilderContext): ServiceCompute[Unit, Unit, ContainerServiceProps] = {
    new ContainerServiceClient(builder, this.getClass.getName, this,
      ContainerServiceProps("mongo", "latest", "mongodb-server", List("27017:27017"))
    )
  }
}
