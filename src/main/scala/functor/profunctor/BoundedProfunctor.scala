package io.github.sgtswagrid.nonsense
package functor.profunctor

import io.github.sgtswagrid.nonsense.util.NoContext
import io.github.sgtswagrid.nonsense.functor.contravariant.BoundedContravariant
import io.github.sgtswagrid.nonsense.functor.covariant.{
  BoundedFunctor, ContextFunctor, Functor, PartialFunctor,
}

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
] extends BoundedFunctor[[o] =>> Self[I, o], Codomain, O],
          BoundedContravariant[[i] =>> Self[i, O], Domain, I]:

  /**
    * ## `dimap` (from [[Profunctor]])
    *
    * [[dimap]] combines both [[map]] (from [[Functor]]) and [[contramap]] (from
    * [[Contravariant]]) into a single operator.
    *
    * Used to simultaneously map both the input to and output from this
    * pipeline.
    *
    * @param pre
    *   The input transformation (to [[I]]).
    *
    * @param post
    *   The output transformation (from [[O]]).
    *
    * @tparam i
    *   The new input type to replace [[I]].
    *
    * @tparam o
    *   The new output type to replace [[O]].
    *
    * @return
    *   An extended version of this pipeline, leaving this original unchanged.
    */
  def dimap[i >: Domain, o <: Codomain](pre: i => I)(post: O => o): Self[i, o]

  override final inline def map[o <: Codomain : NoContext]
    (transform: O => o)
    : Self[I, o] = dimap[I, o](identity)(transform)

  override final inline def contramap[i >: Domain : NoContext]
    (transform: i => I)
    : Self[i, O] = dimap[i, O](transform)(identity)
