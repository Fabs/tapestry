package com.tapestry
package framework.primitives

/**
 * The context to which thinks get invoked, used to call between services
 */
trait ExecutionContext {
  def tell[I](address: String, input: I): Unit
  def ask[I, O](address: String, input: I): Either[Error, O]
}
