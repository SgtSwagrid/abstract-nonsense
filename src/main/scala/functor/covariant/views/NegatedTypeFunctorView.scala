package io.github.sgtswagrid.nonsense
package functor.covariant.views

import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor
import scala.reflect.ClassTag

/**
  * A functor that only maps values that don't have a certain type. Obtained by
  * calling [[BoundedFunctor.when]].
  *
  * @param base
  *   The underlying structure.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  *
  * @tparam Codomain
  *   The upper bound on [[Output]] following any [[BoundedFunctor.map]]-like *
  *   operation.
  *
  * @tparam Output
  *   The type of value contained in this structure (e.g. [[Int]]).
  *
  * @tparam Inactive
  *   The subtype of [[Output]] that is left unmodified.
  */
final class NegatedTypeFunctorView[
  +Self[+X],
  -Codomain,
  +Output,
  +Inactive : ClassTag,
]
  (base: BoundedFunctor[Self, Codomain, Output])
  extends BoundedFunctor[
    [X] =>> Self[X | Inactive],
    Codomain,
    Output,
  ]:

  override def map[Post <: Codomain]
    (transform: Output => Post)
    : Self[Post | Inactive] = base.map:
    case value: Inactive => value.asInstanceOf[Post | (Inactive & Codomain)]
    case value           => transform(value)

  override def toString = base.toString
