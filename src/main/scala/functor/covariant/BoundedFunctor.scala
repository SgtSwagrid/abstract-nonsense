package io.github.sgtswagrid.nonsense
package functor.covariant

import io.github.sgtswagrid.nonsense.functor.covariant.PartialFunctor.NoContext
import io.github.sgtswagrid.nonsense.functor.covariant.ops.*
import io.github.sgtswagrid.nonsense.functor.covariant.views.*
import scala.reflect.ClassTag

/**
  * ## Bounded Functors
  *
  * A bounded functor is a restricted [[Functor]] that can only contain values
  * with certain properties.
  *
  * ### Constraints
  *
  *   1. An upper bound [[Codomain]] on the type [[X]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  *
  * @tparam Codomain
  *   The upper bound on [[X]] (e.g. [[Any]]).
  *
  * @tparam X
  *   The type of value contained or produced (e.g. [[Int]]).
  *
  * @see
  *   If a context bound is needed, use [[ContextFunctor]] or [[PartialFunctor]]
  *   instead.
  */
trait BoundedFunctor[+Self[+_], -Codomain, +X <: Codomain]
  extends PartialFunctor[Self, Codomain, NoContext, X],
          WhenOps[Self, Codomain, X],
          WhenTypeOps[Self, Codomain, X]:

  override final inline def map[Y <: Codomain : NoContext]
    (transform: X => Y)
    : Self[Y] = mapImpl[Y](transform)

  protected def mapImpl[Y <: Codomain](transform: X => Y): Self[Y]

  override final inline def when
    (condition: X => Boolean)
    : ConditionalFunctorView[Self, Codomain, X] =
    ConditionalFunctorView(this, condition)

  override final inline def when[Active <: Codomain : ClassTag]
    : TypeFunctorView[Self, Codomain, X, Active] =
    new TypeFunctorView[Self, Codomain, X, Active](this)

  override final inline def whenNot[Inactive <: Codomain : ClassTag]
    : NegatedTypeFunctorView[Self, Codomain, X, Inactive] =
    new NegatedTypeFunctorView[Self, Codomain, X, Inactive](this)

object BoundedFunctor:

  /** A [[BoundedFunctor]] that never contains any value. */
  trait Empty[+Self : ValueOf, -Codomain]
    extends BoundedFunctor[[_] =>> Self, Codomain, Nothing]:

    override protected inline def mapImpl[Y <: Codomain]
      (transform: Nothing => Y)
      : Self = valueOf[Self]
