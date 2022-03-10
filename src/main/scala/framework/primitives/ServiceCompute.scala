package com.tapestry
package framework.primitives

import org.json4s._
import org.json4s.jackson.JsonMethods._

/**
 *
 * The basic compute primitive that all services must have
 *
 * @tparam I Service Input
 * @tparam O Service Output
 * @tparam P Service Props
 */
trait ServiceCompute[I, O, P] {
  def address(): Address
  def props(): P
}
