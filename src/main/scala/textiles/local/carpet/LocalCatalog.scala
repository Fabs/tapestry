package com.tapestry
package textiles.local.carpet

import framework.primitives.{Address, ServiceRef}

import scala.collection.mutable._

class LocalCatalog {
  val registry: HashMap[Address, ServiceRef[_, _, _, _, _]] = HashMap.empty

  def find(address: Address): Option[ServiceRef[_, _, _, _, _]] = {
    registry.get(address)
  }

  def bind[I, O](address: Address, service: ServiceRef[_, _, _, _, _]): Unit =
    registry.put(address, service)

  override def toString() = registry.toString()
}
