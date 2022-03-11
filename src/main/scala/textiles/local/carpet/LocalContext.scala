package com.tapestry
package textiles.local.carpet

import framework.primitives.{Address, ExecutionContext, Service}

class LocalContext(catalog: LocalCatalog) extends ExecutionContext {
  implicit val context: ExecutionContext = this

  override def tell[I](address: String, input: I): Unit = {
    ask(address, input).left.map(println(_))
  }

  override def ask[I, O](address: String, input: I): Either[Error, O] = {
    val result = catalog.find(Address(address))
      .map(ref => ref.service.asInstanceOf[Service[I, O, _]].execute(input))

    Either.cond(result.isDefined, result.get, new Error(s"Could not find $address"))
  }
}
