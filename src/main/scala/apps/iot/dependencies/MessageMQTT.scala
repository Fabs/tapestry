package com.tapestry
package apps.iot.dependencies

import framework.primitives.{BuilderContext, ExecutionContext, Service, ServiceCompute}

import com.tapestry.apps.iot.runtime.{ContainerServiceClient, ContainerServiceProps}

class MessageMQTT extends Service[Unit, Unit, ContainerServiceProps] {
  override def execute(in: Unit)(implicit context: ExecutionContext): Unit = ???

  override def configure()(implicit builder: BuilderContext): ServiceCompute[Unit, Unit, ContainerServiceProps] = {
    new ContainerServiceClient(builder, this.getClass.getName, this,
      ContainerServiceProps("eclipse-mosquitto", "latest", "mqtt-server",
        ports = List("1883:1883"),
        volumes = List("./mosquitto/mosquitto.conf:/mosquitto/config/mosquitto.conf")
      )
    )
  }
}
