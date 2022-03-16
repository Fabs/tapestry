package com.tapestry
package textiles.local.carpet

import framework.primitives.{Address, Service}

import scala.collection.mutable._

class LocalCatalog {
  val registry: HashMap[Address, Service[_, _, _]] = HashMap.empty

  def find(address: Address): Option[Service[_, _, _]] = {
    registry.get(address)
  }

  def bind[I, O](address: Address, service: Service[_, _, _]): Unit =
    registry.put(address, service)

  override def toString() = registry.toString()
}
