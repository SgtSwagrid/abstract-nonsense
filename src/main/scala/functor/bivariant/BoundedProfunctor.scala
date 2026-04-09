package io.github.sgtswagrid.nonsense
package functor.bivariant

import io.github.sgtswagrid.nonsense.functor.contravariant.BoundedContravariant
import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor

/**
  * A restricted [[Profunctor]] that can only handle particular values.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Transformation`).
  *
  * @tparam Domain
  *   The lower bound on [[Input]] following any [[contramap]]-like operation.
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
  +Self[-I, +O],
  +Domain,
  -Codomain,
  -Input >: Domain,
  +Output <: Codomain,
] extends BoundedFunctor[[O] =>> Self[Input, O], Codomain, Output],
          BoundedContravariant[[I] =>> Self[I, Output], Domain, Input]:

  /** Simultaneously transform both the input to and output from this structure. */
  def dimap[Pre >: Domain, Post <: Codomain]
    (transformInput: Pre => Input)
    (transformOutput: Output => Post)
    : Self[Pre, Post]

  override final inline def map[Post <: Codomain]
    (transform: Output => Post)
    : Self[Input, Post] = dimap[Input, Post](identity)(transform)

  override final inline def contramap[Pre >: Domain]
    (transform: Pre => Input)
    : Self[Pre, Output] = dimap[Pre, Output](transform)(identity)
