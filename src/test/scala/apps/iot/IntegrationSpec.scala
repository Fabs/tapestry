package com.tapestry
package apps.iot

import collection.mutable.Stack
import org.scalatest._
import flatspec._
import matchers._

import textiles.local.carpet.LocalCarpet
import com.tapestry.apps.iot.service._

class IntegrationSpec extends AnyFlatSpec with should.Matchers  {
  "A Local stack" should "start with all services" in {
    val local = new LocalCarpet("com.tapestry.apps.iot.service")
    local.run()
    val context = local.context
    context.tell(classOf[HelloService].getCanonicalName, ())

    context.ask(classOf[SessionRedis].getCanonicalName, ()) should be (Right("PONG"))
    context.ask(classOf[DataMongoDb].getCanonicalName, ()) should be (Right("testdb"))

    local.stop()
  }
}
