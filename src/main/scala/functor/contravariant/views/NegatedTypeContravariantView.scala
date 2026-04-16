package io.github.sgtswagrid.nonsense
package functor.contravariant.views

import io.github.sgtswagrid.nonsense.functor.contravariant.BoundedContravariant
import io.github.sgtswagrid.nonsense.util.NoContext
import scala.reflect.ClassTag

/**
  * A contravariant functor that skips inputs of a certain type, leaving them
  * unchanged.
  */
final class NegatedTypeContravariantView[
  +Self[-_],
  +Domain,
  -X >: Domain,
  Inactive >: Domain : ClassTag,
]
  (base: BoundedContravariant[Self, Domain, X])
  extends BoundedContravariant[[i] =>> Self[i | Inactive], Domain, X]:

  override def contramap[Y >: Domain : NoContext]
    (transform: Y => X)
    : Self[Y | Inactive] = base.contramap[Y | Inactive]:
    case i: Inactive => i.asInstanceOf[X]
    case y: Y        => transform(y)

  override def toString = base.toString
