package com.tapestry
package apps.iot.dependencies

import framework.primitives.{BuilderContext, ExecutionContext, Service, ServiceCompute}

import com.tapestry.apps.iot.runtime.{ContainerServiceClient, ContainerServiceProps}

class SoftwareDevice extends Service[Unit, Unit, ContainerServiceProps] {
  override def execute(in: Unit)(implicit context: ExecutionContext): Unit = ???

  override def configure()(implicit builder: BuilderContext): ServiceCompute[Unit, Unit, ContainerServiceProps] = {
    val port = "8099"
    new ContainerServiceClient(builder, this.getClass.getName, this,
      ContainerServiceProps("fabu/fake-device", "latest", "fake-device", List(s"$port:$port"),
        environment = Map("PORT" -> s"$port")
      )
    )
  }
}
