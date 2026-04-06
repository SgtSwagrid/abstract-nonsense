package io.github.sgtswagrid.nonsense
package functor.contravariant

/**
  * A shape for [[ops.BoundedContraFunctorOps]].
  *
  * @tparam Self
  *   The kind of structure that this describes (e.g. `Sink`).
  *
  * @tparam Domain
  *   The lower bound on [[Input]] following any [[contraMap]]-like operation.
  */
trait BoundedContraFunctorKind[+Self[-_], +Domain]
