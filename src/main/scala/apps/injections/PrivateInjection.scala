package com.tapestry
package apps.injections

import com.tapestry.framework.primitives.Service

import scala.collection.mutable._

object PrivateInjection {
  // Private code injects itself here by modifying this list
  var services = List.empty[Service[_,_,_]]
}
