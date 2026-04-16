package io.github.sgtswagrid.nonsense
package functor.covariant.views

import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor
import scala.reflect.ClassTag

/** A functor that only maps values that have a certain type. */
final class TypeFunctorView[
  +Self[+_ <: Codomain],
  -Codomain,
  +X <: Codomain,
  +Active <: Codomain : ClassTag,
]
  (base: BoundedFunctor[Self, Codomain, X])
  extends BoundedFunctor[
    [Y <: Codomain] =>> Self[Y | X],
    Codomain,
    Active,
  ]:

  override inline def mapImpl[Y <: Codomain]
    (transform: Active => Y)
    : Self[Y | X] = base.map:
    case value: Active => transform(value)
    case value         => value.asInstanceOf[X & Codomain]

  override def toString = base.toString
