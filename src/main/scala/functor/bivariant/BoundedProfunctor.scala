package io.github.sgtswagrid.nonsense
package functor.bivariant

import io.github.sgtswagrid.nonsense.functor.contravariant.BoundedContraFunctor
import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor

/**
  * A restricted [[Profunctor]] that can only handle particular values.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Transformation`).
  *
  * @tparam Domain
  *   The lower bound on [[Input]] following any [[contraMap]]-like operation.
  *
  * @tparam Codomain
  *   The upper bound on [[Output]] following any [[map]]-like operation.
  *
  * @tparam Input
  *   The type of value that can be handled by this structure (e.g. `Event`).
  *
  * @tparam Output
  *   The type of value that this structure can produce (e.g. `Action`).
  */
trait BoundedProfunctor[
  +Self[-I, +O] <: BoundedProfunctor[Self, Domain, Codomain, I, O],
  +Domain,
  -Codomain,
  -Input,
  +Output,
] extends BoundedFunctor[[O] =>> Self[Input, O], Codomain, Output],
          BoundedContraFunctor[[I] =>> Self[I, Output], Domain, Input]:

  /** Simultaneously transform both the input and output to this structure. */
  final inline def dimap[Pre >: Domain, Post <: Codomain]
    (transformInput: Pre => Input)
    (transformOutput: Output => Post)
    : Self[Pre, Post] = contraMap(transformInput).map(transformOutput)
