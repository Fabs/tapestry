package com.tapestry
package company.components

import company.config.DockerImage
import company.{Container, ServiceKind}
import framework.primitives._

import scala.reflect.runtime.universe._

abstract class ContainerServiceClient[I: TypeTag, O: TypeTag] extends Service[I, O, DockerImage] {

  override def address(): Address = Address(this.getClass.getName)

  override def props(): DockerImage = props

  override def build()(implicit builder: BuilderContext): ServiceKind = {
    builder.register(address(), this)

    kind()
  }

  override def kind(): ServiceKind = Container
}
