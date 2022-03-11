package com.tapestry
package textiles.local

import textiles.local.carpet.LocalCarpet

object LocalMain {
  def main(args: Array[String]): Unit = {
    new LocalCarpet("com.tapestry.apps.iot.service").run()
    println("Hello there! I'm just going to wait for an hour and exit.")
  }
}
