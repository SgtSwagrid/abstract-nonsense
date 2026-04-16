package io.github.sgtswagrid.nonsense
package functor.covariant.views

import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor
import io.github.sgtswagrid.nonsense.util.NoContext
import scala.reflect.ClassTag

/** A functor that only maps values that don't have a certain type. */
class NegatedTypeFunctorView[
  +Self[+_ <: Codomain],
  -Codomain,
  +X <: Codomain,
  +Inactive <: Codomain : ClassTag,
]
  (base: BoundedFunctor[Self, Codomain, X])
  extends BoundedFunctor[
    [Y <: Codomain] =>> Self[Y | Inactive],
    Codomain,
    X,
  ]:

  override inline def map[Post <: Codomain : NoContext]
    (transform: X => Post)
    : Self[Post | Inactive] = base.map:
    case value: Inactive => value.asInstanceOf[Post | (Inactive & Codomain)]
    case value           => transform(value)

  override def toString = base.toString
