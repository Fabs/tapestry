package com.tapestry
package framework.primitives

/**
 * A manifestation of a service in tapestry, provides the context in what thing get run and built.
 *
 * Carpets can be local, sample, api, ui, and etc ...
 *
 */
trait Carpet {
  def context(): ExecutionContext
  def builderContext: BuilderContext
  def run(): Unit
}
