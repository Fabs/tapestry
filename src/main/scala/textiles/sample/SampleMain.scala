package com.tapestry
package textiles.sample

object SampleMain {
  def main(args: Array[String]): Unit = {
    new SampleCarpet("com.tapestry.apps.latac.service").run()
    println("Hello there! I'm just going to wait for an hour and exit.")
  }
}
