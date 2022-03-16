package com.tapestry
package textiles.local.carpet

import framework.primitives.{Address, BuilderContext, Service}

class LocalBuildContext extends BuilderContext {
  val catalog = new LocalCatalog()

  override def register[I, O](address: Address, target: Service[_, _, _]): Unit = {
    catalog.bind(address, target)
  }

  def services(): Iterable[Service[_,_,_]] = {
    catalog.registry.values
  }
}
