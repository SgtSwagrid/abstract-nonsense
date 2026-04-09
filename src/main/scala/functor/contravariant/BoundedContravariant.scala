package io.github.sgtswagrid.nonsense
package functor.contravariant

/**
  * A restricted [[Contravariant]] that can only handle particular values.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Sink`).
  *
  * @tparam Domain
  *   The lower bound on [[Input]] following any [[contramap]]-like operation.
  *
  * @tparam Input
  *   The type of value that can be handled by this structure (e.g. `Event`).
  */
trait BoundedContravariant[+Self[-_], +Domain, -Input]:

  /** Transform the input to this structure arbitrarily. */
  def contramap[Result >: Domain](transform: Result => Input): Self[Result]
