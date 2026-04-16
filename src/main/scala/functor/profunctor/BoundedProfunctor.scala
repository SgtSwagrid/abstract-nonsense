package io.github.sgtswagrid.nonsense
package functor.profunctor

import io.github.sgtswagrid.nonsense.functor.contravariant.BoundedContravariant
import io.github.sgtswagrid.nonsense.functor.covariant.{
  BoundedFunctor, ContextFunctor, Functor, PartialFunctor,
}
import io.github.sgtswagrid.nonsense.functor.profunctor.ops.DimapOps
import io.github.sgtswagrid.nonsense.util.NoContext

/**
  * ## Bounded Profunctors
  *
  * A bounded profunctor is a restricted [[Profunctor]] that can only handle
  * values with certain properties.
  *
  * ### Constraints
  *
  *   1. A lower bound [[Domain]] on the input type [[I]].
  *   2. An upper bound [[Codomain]] on the output type [[O]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Function]]).
  *
  * @tparam Domain
  *   The lower bound on [[I]] (e.g. [[Nothing]]).
  *
  * @tparam Codomain
  *   The upper bound on [[O]] (e.g. [[Any]]).
  *
  * @tparam I
  *   The type of value consumed (e.g. [[Int]]).
  *
  * @tparam O
  *   The type of value produced (e.g. [[Int]]).
  */
trait BoundedProfunctor[
  +Self[-_, +_],
  +Domain,
  -Codomain,
  -I >: Domain,
  +O <: Codomain,
] extends DimapOps[Self, Domain, Codomain, I, O],
          BoundedFunctor[[o] =>> Self[I, o], Codomain, O],
          BoundedContravariant[[i] =>> Self[i, O], Domain, I]:

  override final inline def map[o <: Codomain : NoContext]
    (transform: O => o)
    : Self[I, o] = dimap[I, o](identity)(transform)

  override final inline def contramap[i >: Domain : NoContext]
    (transform: i => I)
    : Self[i, O] = dimap[i, O](transform)(identity)
