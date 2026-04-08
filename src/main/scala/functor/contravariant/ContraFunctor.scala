package io.github.sgtswagrid.nonsense
package functor.contravariant

/**
  * A contravariant functor is something that can be contra-mapped over.
  *
  * Any implementation need only define [[contraMap]], and everything else will
  * be derived from it.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Sink`).
  *
  * @tparam Input
  *   The type of value that can be handled by this structure (e.g. `Event`).
  */
trait ContraFunctor[
  +Self[-X] <: ContraFunctor[Self, X],
  -Input,
] extends BoundedContraFunctor[Self, Nothing, Input]
