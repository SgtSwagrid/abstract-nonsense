package io.github.sgtswagrid.nonsense
package functors.covariant

import io.github.sgtswagrid.nonsense.functors.covariant.ops.*
import io.github.sgtswagrid.nonsense.functors.covariant.views.*
import scala.reflect.ClassTag

/**
  * A restricted [[Functor]] that can only contain particular values,
  * constrained by type bounds.
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
trait BoundedFunctor[+Self[+_], -Codomain, +Output <: Codomain]
  extends BoundedContextFunctor[
    Self,
    Codomain,
    [_] =>> DummyImplicit,
    Output,
  ],
          WhenOps[Self, Codomain, Output],
          WhenTypeOps[Self, Codomain, Output]:

  override final inline def map[Post <: Codomain : [_] =>> DummyImplicit]
    (transform: Output => Post)
    : Self[Post] = mapImpl[Post](transform)

  protected def mapImpl[Post <: Codomain](transform: Output => Post): Self[Post]

  override final inline def when
    (condition: Output => Boolean)
    : ConditionalFunctorView[Self, Codomain, Output] =
    ConditionalFunctorView(this, condition)

  override final inline def when[Active <: Codomain : ClassTag]
    : TypeFunctorView[Self, Codomain, Output, Active] =
    new TypeFunctorView[Self, Codomain, Output, Active](this)

  override final inline def whenNot[Inactive <: Codomain : ClassTag]
    : NegatedTypeFunctorView[Self, Codomain, Output, Inactive] =
    new NegatedTypeFunctorView[Self, Codomain, Output, Inactive](this)

object BoundedFunctor:

  /**
    * A [[BoundedFunctor]] that contains no values.
    *
    * @tparam Self
    *   The singleton produced by all [[BoundedFunctor.map]]-like operations.
    *
    * @tparam Codomain
    *   The upper bound on the output of all [[BoundedFunctor.map]]-like
    *   operations.
    */
  trait Empty[+Self : ValueOf, -Codomain]
    extends BoundedFunctor[[_] =>> Self, Codomain, Nothing]:
    override protected inline def mapImpl[Post <: Codomain]
      (transform: Nothing => Post)
      : Self = valueOf[Self]
