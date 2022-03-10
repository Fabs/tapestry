package com.tapestry
package framework.primitives

/**
 * The context in which thinks get build, used to organize infrastructure
 */
trait BuilderContext {
  def register[I, O](address: Address, target: ServiceRef[_, _, _, _, _]): Unit
}
