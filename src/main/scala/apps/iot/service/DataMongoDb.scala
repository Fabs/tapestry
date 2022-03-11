package com.tapestry
package apps.iot.service

import apps.iot.runtime.{ContainerServiceClient, DockerImage}
import framework.primitives.{BuilderContext, Construct, ExecutionContext, Service}

import cats.effect.IO
import mongo4cats.client.MongoClient

import cats.effect.unsafe.implicits.global

class DataMongoDb extends Service[Unit, String, DockerImage] {
  override def execute(in: Unit)(implicit context: ExecutionContext): String = {
    MongoClient.fromConnectionString[IO]("mongodb://localhost:27017").use { client =>
      for {
        db <- client.getDatabase("testdb")
      } yield (db.name)
    }.unsafeRunSync() //Hum, scape monad you shall not.
  }

  override def configure()(implicit builder: BuilderContext): Construct[Unit, String, DockerImage] = {
    new ContainerServiceClient(builder, this.getClass.getName, this,
      DockerImage("mongo", "latest", "mongodb-server", List("27017:27017"))
    )
  }
}
