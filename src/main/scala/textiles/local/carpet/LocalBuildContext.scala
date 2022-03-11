package com.tapestry
package textiles.local.carpet

import framework.primitives.{Address, BuilderContext, ServiceRef}

class LocalBuildContext extends BuilderContext {
  val catalog = new LocalCatalog()

  override def register[I, O](address: Address, target: ServiceRef[_, _, _, _, _]): Unit = {
    catalog.bind(address, target)
  }

  def services(): Iterable[ServiceRef[_,_,_,_,_]] = {
    catalog.registry.values
  }
}
