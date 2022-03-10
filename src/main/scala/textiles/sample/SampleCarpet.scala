package com.tapestry
package textiles.sample

import apps.latac.service.HelloService
import framework.primitives.Carpet

import com.tapestry.framework.utils.ServiceLoader

class SampleCarpet(catalogPackage: String) extends Carpet {
  implicit val builderContext: SampleBuildContext = new SampleBuildContext
  val services = ServiceLoader.servicesFromCatalog(catalogPackage)
  println(services.length)
  services.foreach(println _)
  services.map(_.configure())

  implicit val context: SampleContext = new SampleContext(builderContext.catalog)

  override def run(): Unit = {
    println(builderContext.catalog)
    context.tell(classOf[HelloService].getCanonicalName, ())
  }
}
