package io.github.sgtswagrid.nonsense
package functor.contravariant.views

import io.github.sgtswagrid.nonsense.functor.contravariant.BoundedContravariant
import io.github.sgtswagrid.nonsense.util.NoContext
import scala.reflect.ClassTag

/** A contravariant functor that only transforms inputs of a certain type. */
final class TypeContravariantView[
  +Self[-_],
  +Domain,
  -X >: Domain,
  Active >: Domain : ClassTag,
]
  (base: BoundedContravariant[Self, Domain, X])
  extends BoundedContravariant[[i] =>> Self[i | X], Domain, Active]:

  override def contramap[Y >: Domain : NoContext]
    (transform: Y => Active)
    : Self[Y | X] = base.contramap[Y | X]:
    case y: Y => transform(y).asInstanceOf[X]
    case x: X => x

  override def toString = base.toString
