package com.tapestry
package apps.iot.service

import apps.iot.runtime.{ContainerServiceClient, DockerImage}
import framework.primitives.{BuilderContext, ExecutionContext, Service, Construct}

import cats.effect._
import cats.implicits._
import dev.profunktor.redis4cats.Redis
import dev.profunktor.redis4cats.effect.Log.Stdout._

import cats.effect.unsafe.implicits.global

class SessionRedis extends Service[Unit, String, DockerImage] {
  override def execute(in: Unit)(implicit context: ExecutionContext): String = {
    Redis[IO].utf8("redis://localhost").use { redis =>
      for {
        pong <- redis.ping
      } yield (pong)
    }.unsafeRunSync() //Hum, scape monad you shall not.
  }

  override def configure()(implicit builder: BuilderContext): Construct[Unit, String, DockerImage] = {
    new ContainerServiceClient(builder, this.getClass.getName, this,
      DockerImage("redis", "latest", "redis-server", List("6379:6379"))
    )
  }
}
