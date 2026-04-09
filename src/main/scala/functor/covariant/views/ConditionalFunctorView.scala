package io.github.sgtswagrid.nonsense
package functor.covariant.views

import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor

/**
  * A functor that only maps values that satisfy a given condition. Obtained by
  * calling [[when]].
  *
  * @param base
  *   The underlying structure.
  *
  * @param condition
  *   The condition that determines which elements are to be modified.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  *
  * @tparam Codomain
  *   The upper bound on [[Output]] following any [[map]]-like operation.
  *
  * @tparam Output
  *   The type of value contained in this structure (e.g. [[Int]]).
  */
final class ConditionalFunctorView[+Self[+_], -Codomain, +Output <: Codomain]
  (
    base: BoundedFunctor[Self, Codomain, Output],
    condition: Output => Boolean,
  )
  extends BoundedFunctor[[X] =>> Self[X | Output], Codomain, Output]:

  override protected inline def mapImpl[Post <: Codomain]
    (transform: Output => Post)
    : Self[Post | Output] = base.map:
    case value if condition(value) => transform(value)
    case value                     => value

  override def toString = base.toString
