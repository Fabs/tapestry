package com.tapestry
package apps.iot.service.dependencies

import company.components.ContainerServiceClient
import company.config.DockerImage
import framework.primitives.ExecutionContext

import cats.effect._
import cats.effect.unsafe.implicits.global
import dev.profunktor.redis4cats.Redis
import dev.profunktor.redis4cats.effect.Log.Stdout._

class SessionRedis extends ContainerServiceClient[Unit, String] {
  override def execute(in: Unit)(implicit context: ExecutionContext): String = {
    Redis[IO].utf8("redis://localhost").use { redis =>
      for {
        pong <- redis.ping
      } yield (pong)
    }.unsafeRunSync() //Hum, scape monad you shall not.
  }

  override val props: DockerImage = DockerImage("redis", "latest", "redis-server", List("6379:6379"))
}
