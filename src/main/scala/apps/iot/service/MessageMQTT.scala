package com.tapestry
package apps.iot.service

import company.Workplace.HACKS
import company.components.ContainerServiceClient
import company.config.DockerImage
import framework.primitives.ExecutionContext

class MessageMQTT extends ContainerServiceClient[Unit, Unit] {
  override def execute(in: Unit)(implicit context: ExecutionContext): Unit = ???

  override val props: DockerImage = DockerImage("eclipse-mosquitto", "latest", "mqtt-server",
    ports = List("1883:1883"),
    volumes = List(s"$HACKS/mosquitto/mosquitto.conf:/mosquitto/config/mosquitto.conf")
  )
}
