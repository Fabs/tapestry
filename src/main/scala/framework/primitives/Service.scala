package com.tapestry
package framework.primitives

import company.ServiceKind

/**
 * The core component is what actually gets run to produce results
 *
 * @tparam Input what the service receives
 * @tparam Output what the service outputs
 * @tparam Config the configuration properties of the service
 */
trait Service[Input, Output, Config] {
  def execute(in: Input)(implicit context: ExecutionContext) : Output
  def build()(implicit builder: BuilderContext): ServiceKind
  def props(): Config
  def kind(): ServiceKind
  def address(): Address
}
// Should need an Input, Output, Runtime and Props
