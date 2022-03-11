package com.tapestry
package textiles.local.carpet

import apps.iot.runtime.DockerImage
import company.Container
import company.Workplace.WORKPLACE
import framework.primitives.{Carpet, Construct}
import framework.utils.ServiceLoader

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature
import com.fasterxml.jackson.databind.{ObjectMapper, PropertyNamingStrategy}
import com.tapestry.apps.iot.service.HelloService
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import java.io.File
import scala.collection.mutable
import sys.process._

class LocalCarpet(catalogPackage: String) extends Carpet {
  implicit val builderContext: LocalBuildContext = new LocalBuildContext
  val services = ServiceLoader.servicesFromCatalog(catalogPackage)
  services.foreach(s => {
    s.configure().build(builderContext)
  })

  val dockerServices = mutable.Map.empty[String, DockerImage]
  builderContext.catalog.registry.foreach(kv => {
    val service = kv._2
    service.serviceType match {
      case Container => {
        val props = service.runtime.asInstanceOf[Construct[_, _, DockerImage]].props()
        dockerServices.put(props.containerName, props)
      }
      case _ =>
    }
  })
  val dockerCompose = Map("services" -> dockerServices)

  val mapper = new ObjectMapper((new YAMLFactory()).disable(Feature.WRITE_DOC_START_MARKER).enable(Feature
    .INDENT_ARRAYS).enable(Feature.USE_PLATFORM_LINE_BREAKS))
  mapper.registerModule(DefaultScalaModule)
  mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
  mapper.setSerializationInclusion(Include.NON_NULL)
  mapper.writeValue(new File(s"$WORKPLACE/docker-compose.yaml"), dockerCompose)
  sys.process.Process(Seq("docker-compose","up", "-d"), new java.io.File(WORKPLACE)).!!

  implicit val context: LocalContext = new LocalContext(builderContext.catalog)

  override def run(): Unit = {
    context.tell(classOf[HelloService].getCanonicalName, ())
  }

  def stop(): Unit = {
    sys.process.Process(Seq("docker-compose","down"), new java.io.File(WORKPLACE)).!!
  }
}
