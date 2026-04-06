package io.github.sgtswagrid.nonsense
package functor.bivariant

/**
  * A shape for [[ops.BoundedBiFunctorOps]].
  *
  * @tparam Self
  *   The kind of structure that this describes (e.g. `Transformation`).
  *
  * @tparam Domain
  *   The lower bound on [[Input]] following any [[contraMap]]-like operation.
  *
  * @tparam Codomain
  *   The upper bound on [[Output]] following any [[map]]-like operation.
  */
trait BoundedBiFunctorKind[+Self[-_, +_], +Domain, -Codomain]
