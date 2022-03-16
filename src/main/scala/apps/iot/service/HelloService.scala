package com.tapestry
package apps.iot.service

import company.components.CloudFunction
import framework.primitives.ExecutionContext

class HelloService extends CloudFunction[Unit, String] {
  override def execute(in: Unit)(implicit context: ExecutionContext): String = {
    println("Hello Ukraine")

    "Hello Ukraine"
  }
}
