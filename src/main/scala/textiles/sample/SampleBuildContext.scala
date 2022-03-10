package com.tapestry
package textiles.sample

import framework.primitives.{Address, BuilderContext, ServiceRef}

class SampleBuildContext extends BuilderContext {
  val catalog = new MemoryCatalog()

  override def register[I, O](address: Address, target: ServiceRef[_, _, _, _, _]): Unit = {
    catalog.bind(address, target)
  }
}
