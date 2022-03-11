package com.tapestry
package apps.iot.service

import apps.iot.runtime.{ContainerServiceClient, DockerImage}
import framework.primitives.{Address, BuilderContext, ExecutionContext, Service, Construct}

class DataMongoDb extends Service[Unit, Unit, DockerImage] {
  override def execute(in: Unit)(implicit context: ExecutionContext): Unit = ???

  override def configure()(implicit builder: BuilderContext): Construct[Unit, Unit, DockerImage] = {
    new ContainerServiceClient(builder, this.getClass.getName, this,
      DockerImage("mongo", "latest", "mongodb-server", List("27017:27017"))
    )
  }
}
