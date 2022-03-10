package com.tapestry
package apps.iot.runtime

import framework.primitives._

import scala.reflect.runtime.universe._

class ContainerServiceClient[I: TypeTag, O: TypeTag](builder: BuilderContext, address: String,
                                                              service: Service[I, O, ContainerServiceProps],
                                                              props: ContainerServiceProps) extends
ServiceCompute[I, O,
  ContainerServiceProps] {
  builder.register(address(), ServiceRef(service, this, typeTag[I].tpe, typeTag[O].tpe, props.getClass))

  override def address(): Address = Address(address)
  override def props(): ContainerServiceProps = props
}
