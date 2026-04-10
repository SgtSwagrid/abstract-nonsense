package io.github.sgtswagrid.nonsense
package functors.covariant.views

import io.github.sgtswagrid.nonsense.functors.covariant.BoundedFunctor
import scala.reflect.ClassTag

/**
  * A functor that only maps values that have a certain type. Obtained by
  * calling [[when]].
  *
  * @param base
  *   The underlying structure.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  *
  * @tparam Codomain
  *   The upper bound on [[Output]] following any [[map]]-like operation.
  *
  * @tparam Output
  *   The type of value contained in this structure (e.g. [[Int]]).
  *
  * @tparam Active
  *   The subtype of [[Output]] that is being modified.
  */
final class TypeFunctorView[
  +Self[+_],
  -Codomain,
  +Output <: Codomain,
  +Active <: Codomain : ClassTag,
]
  (base: BoundedFunctor[Self, Codomain, Output])
  extends BoundedFunctor[[X] =>> Self[X | Output], Codomain, Active]:

  override inline def mapImpl[Post <: Codomain]
    (transform: Active => Post)
    : Self[Post | Output] = base.map:
    case value: Active => transform(value)
    case value         => value.asInstanceOf[Output & Codomain]

  override def toString = base.toString
