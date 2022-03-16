package com.tapestry
package apps.iot.service.dependencies

import company.components.ContainerServiceClient
import company.config.DockerImage
import company.{Container, ServiceKind}
import framework.primitives.ExecutionContext

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import mongo4cats.client.MongoClient

class DataMongoDb extends ContainerServiceClient[Unit, String] {
  override def execute(in: Unit)(implicit context: ExecutionContext): String = {
    MongoClient.fromConnectionString[IO]("mongodb://localhost:27017").use { client =>
      for {
        db <- client.getDatabase("testdb")
      } yield (db.name)
    }.unsafeRunSync() //Hum, scape monad you shall not.
  }

  override def props() =
    DockerImage("mongo", "latest", "mongodb-server", List("27017:27017"))

  override def kind(): ServiceKind = Container
}
