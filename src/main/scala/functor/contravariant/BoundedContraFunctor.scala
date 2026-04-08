package io.github.sgtswagrid.nonsense
package functor.contravariant

/**
  * A restricted [[ContraFunctor]] that can only handle particular values.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Sink`).
  *
  * @tparam Domain
  *   The lower bound on [[Input]] following any [[contraMap]]-like operation.
  *
  * @tparam Input
  *   The type of value that can be handled by this structure (e.g. `Event`).
  */
trait BoundedContraFunctor[+Self[-_], +Domain, -Input]:

  /** Transform the input to this structure arbitrarily. */
  def contraMap[Result >: Domain](transform: Result => Input): Self[Result]
