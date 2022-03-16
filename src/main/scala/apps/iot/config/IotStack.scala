package com.tapestry
package apps.iot.config

import framework.utils.ServiceLoader

object IotStack {
  val services = ServiceLoader.servicesFromPackage("com.tapestry.apps.iot.service")
}
