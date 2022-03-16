package com.tapestry
package textiles.local

import textiles.local.carpet.LocalCarpet

import com.tapestry.apps.injections.PrivateInjection
import com.tapestry.apps.iot.config.IotStack

object LocalMain {
  def main(args: Array[String]): Unit = {
    new LocalCarpet(
      IotStack.services ++
      PrivateInjection.services
    ).run()
    println("Hello there! I'm just going to wait for an hour and exit.")
  }
}
