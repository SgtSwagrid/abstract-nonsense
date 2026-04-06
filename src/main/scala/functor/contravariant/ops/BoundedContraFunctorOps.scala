package io.github.sgtswagrid.nonsense
package functor.contravariant.ops

import io.github.sgtswagrid.nonsense.functor.contravariant.BoundedContraFunctorKind

/**
  * A restricted [[ContraFunctorOps]] that can only handle particular values.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Sink`).
  *
  * @tparam Input
  *   The type of value that can be handled by this structure (e.g. `Event`).
  *
  * @tparam Domain
  *   The lower bound on [[Input]] following any [[contraMap]]-like operation.
  */
trait BoundedContraFunctorOps[+Self[-_], -Input, +Domain]
  extends BoundedContraFunctorKind[Self, Domain]:

  /** Transform the input to this structure arbitrarily. */
  def contraMap[Result >: Domain](transform: Result => Input): Self[Result]
