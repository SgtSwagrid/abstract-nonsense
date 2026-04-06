package io.github.sgtswagrid.nonsense
package functor.bivariant.ops

import io.github.sgtswagrid.nonsense.functor.bivariant.BoundedBiFunctorKind
import io.github.sgtswagrid.nonsense.functor.contravariant.ops.{
  BoundedContraFunctorOps, ContraFunctorOps,
}
import io.github.sgtswagrid.nonsense.functor.covariant.ops.BoundedFunctorOps

/**
  * A restricted [[BiFunctorOps]] that can only handle particular values.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Transformation`).
  *
  * @tparam Input
  *   The type of value that can be handled by this structure (e.g. `Event`).
  *
  * @tparam Output
  *   The type of value that this structure can produce (e.g. `Action`).
  *
  * @tparam Domain
  *   The lower bound on [[Input]] following any [[contraMap]]-like operation.
  *
  * @tparam Codomain
  *   The upper bound on [[Output]] following any [[map]]-like operation.
  */
trait BoundedBiFunctorOps[
  +Self[-_, +_],
  -Input,
  +Output,
  +Domain,
  -Codomain,
] extends BoundedBiFunctorKind[Self, Domain, Codomain],
          BoundedFunctorOps[[O] =>> Self[Input, O], Output, Codomain],
          BoundedContraFunctorOps[[I] =>> Self[I, Output], Input, Domain]
