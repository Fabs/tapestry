package com.tapestry
package textiles.local.carpet

import apps.iot.service.HelloService
import company.Workplace.WORKPLACE
import company.config.DockerImage
import framework.primitives.{Carpet, Service}

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.{ObjectMapper, PropertyNamingStrategy}
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.tapestry.company.Container

import java.io.File
import scala.collection.mutable

class LocalCarpet(services: List[Service[_, _, _]]) extends Carpet {
  implicit val builderContext: LocalBuildContext = new LocalBuildContext

  services.foreach(s => {
    s.build()
  })

  val dockerServices = mutable.Map.empty[String, DockerImage]
  builderContext.catalog.registry.foreach(kv => {
    val service = kv._2
    service.kind() match {
      case Container => {
        val props: DockerImage = service.props().asInstanceOf[DockerImage]
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
  sys.process.Process(Seq("docker-compose", "up", "-d"), new java.io.File(WORKPLACE)).!!

  implicit val context: LocalContext = new LocalContext(builderContext.catalog)

  override def run(): Unit = {
    context.tell(classOf[HelloService].getCanonicalName, ())
  }

  def stop(): Unit = {
    sys.process.Process(Seq("docker-compose", "down"), new java.io.File(WORKPLACE)).!!
  }
}
