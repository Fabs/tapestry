package com.tapestry
package apps.iot.service

import apps.iot.runtime.{ContainerServiceClient, DockerImage}
import company.Workplace.HACKS
import framework.primitives.{BuilderContext, ExecutionContext, Service, Construct}

class MessageMQTT extends Service[Unit, Unit, DockerImage] {
  override def execute(in: Unit)(implicit context: ExecutionContext): Unit = ???

  override def configure()(implicit builder: BuilderContext): Construct[Unit, Unit, DockerImage] = {
    new ContainerServiceClient(builder, this.getClass.getName, this,
      DockerImage("eclipse-mosquitto", "latest", "mqtt-server",
        ports = List("1883:1883"),
        volumes = List(s"$HACKS/mosquitto/mosquitto.conf:/mosquitto/config/mosquitto.conf")
      )
    )
  }
}
