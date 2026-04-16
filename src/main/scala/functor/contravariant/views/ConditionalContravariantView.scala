package io.github.sgtswagrid.nonsense
package functor.contravariant.views

import io.github.sgtswagrid.nonsense.functor.contravariant.BoundedContravariant
import io.github.sgtswagrid.nonsense.util.NoContext

/**
  * A contravariant functor that only transforms inputs satisfying a given
  * condition. Inputs not satisfying the condition are passed through as-is,
  * which requires them to already be of the consumed type [[X]].
  *
  * @param base
  *   The underlying consumer.
  *
  * @param condition
  *   The condition checked on each input after transformation to [[X]].
  */
final class ConditionalContravariantView[+Self[-_], +Domain, -X >: Domain]
  (
    base: BoundedContravariant[Self, Domain, X],
    condition: X => Boolean,
  )
  extends BoundedContravariant[[i] =>> Self[i | X], Domain, X]:

  override def contramap[Y >: Domain : NoContext]
    (transform: Y => X)
    : Self[Y | X] = base.contramap[Y | X]:
    case y: Y if condition(transform(y)) => transform(y)
    case x: X                            => x

  override def toString = base.toString
