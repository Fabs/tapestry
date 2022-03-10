package com.tapestry
package textiles.local

import textiles.sample.SampleCarpet

import com.tapestry.framework.utils.ServiceLoader
import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner

object LocalMain {
  def main(args: Array[String]): Unit = {
    val services = ServiceLoader.servicesFromCatalog("com.tapestry")
    println(services.length)
    services.foreach(println _)
  }
}
