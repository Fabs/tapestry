package com.tapestry
package textiles.local

import apps.injections.PrivateInjection
import apps.iot.config.IotStack
import textiles.local.carpet.LocalCarpet

import picocli.CommandLine
import picocli.CommandLine.{Command, Option}

import java.util.concurrent.Callable

@Command(name = "local", mixinStandardHelpOptions = true, version = Array("checksum 4.0"),
  description = Array("Stats a local deploy"))
class LocalMain(carpet: LocalCarpet) extends Callable[Int] {
  @Option(names = Array("-c"))
  var cancel = false

  def call(): Int = {
    //Arrgh that is uglly
    if (cancel) {
      carpet.stop()
    } else {
      carpet.run()
      Thread.currentThread().join()
    }
    0
  }
}

object LocalMain {
  val carpet = new LocalCarpet(
    IotStack.services ++
      PrivateInjection.services
  )

  def main(args: Array[String]): Unit = {
    val res = new CommandLine(new LocalMain(carpet)).execute(args: _*)
    Runtime.getRuntime().addShutdownHook(new Thread {
      override def run = {
        carpet.stop()
        println("Shutdown")
      }
    })
    System.exit(res)
  }
}
