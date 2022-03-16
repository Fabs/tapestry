package com.tapestry
package apps.iot.service.dependencies

import company.components.ContainerServiceClient
import company.config.DockerImage
import framework.primitives.ExecutionContext

class SoftwareDevice extends ContainerServiceClient[Unit, Unit] {
  override def execute(in: Unit)(implicit context: ExecutionContext): Unit = ???

  private val port = "8099"
  override val props: DockerImage = DockerImage("fabs/fake-device", "latest", "fake-device", List(s"$port:$port"),
    environment = Map("PORT" -> s"$port")
  )
}
