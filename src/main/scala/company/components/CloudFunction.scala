package com.tapestry
package company.components

import company.config.BasicCompute
import company.{Lambda, ServiceKind}
import framework.primitives.{Address, BuilderContext, Service}

abstract class CloudFunction[I, O] extends Service[I, O, BasicCompute] {
  override def address(): Address = Address(this.getClass.getName)

  override def build()(implicit builder: BuilderContext): ServiceKind = {
    builder.register(address(), this)

    kind()
  }

  override def props(): BasicCompute = BasicCompute(address().uri)

  override def kind(): ServiceKind = Lambda
}
