package com.tapestry
package apps.iot.runtime

import framework.primitives._

import com.tapestry.company.Container

import scala.reflect.runtime.universe._

class ContainerServiceClient[I: TypeTag, O: TypeTag](builder: BuilderContext, address: String,
                                                     service: Service[I, O, DockerImage],
                                                     props: DockerImage) extends
Construct[I, O,
  DockerImage] {

  override def address(): Address = Address(address)
  override def props(): DockerImage = props

  override def build(implicit builder: BuilderContext): Unit = {
    builder.register(address(), ServiceRef(service, this, typeTag[I].tpe, typeTag[O].tpe, props.getClass, Container))
  }
}
