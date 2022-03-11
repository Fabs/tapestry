package com.tapestry
package apps.iot.service

import framework.primitives.{BuilderContext, ExecutionContext, Service, Construct}

import com.tapestry.apps.iot.runtime.{ContainerServiceClient, DockerImage}

class SoftwareDevice extends Service[Unit, Unit, DockerImage] {
  override def execute(in: Unit)(implicit context: ExecutionContext): Unit = ???

  override def configure()(implicit builder: BuilderContext): Construct[Unit, Unit, DockerImage] = {
    val port = "8099"
    new ContainerServiceClient(builder, this.getClass.getName, this,
      DockerImage("fabs/fake-device", "latest", "fake-device", List(s"$port:$port"),
        environment = Map("PORT" -> s"$port")
      )
    )
  }
}
