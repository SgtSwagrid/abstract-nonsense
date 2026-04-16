package io.github.sgtswagrid.nonsense
package functor.covariant

import io.github.sgtswagrid.nonsense.functor.covariant.ops.{DeepOps, WhenOps}
import io.github.sgtswagrid.nonsense.functor.covariant.views.{
  DeepFunctorView, FunctorConditionalView, FunctorNegatedTypeView,
  FunctorTypeView,
}
import io.github.sgtswagrid.nonsense.util.{anyTop, NoContext}
import scala.reflect.ClassTag

/**
  * ## Functors
  *
  * A covariant functor is an object with a [[map]] operator.
  *
  * They typically represent structures that contain or produce values.
  *
  * ### Covariant Functor Laws
  *
  * The following covariant functor laws should hold in any implementation:
  *
  * #### Identity Law
  * ```scala
  * fa.map(identity) == fa
  * ```
  *
  * #### Composition Law
  * ```scala
  * fa.map(f).map(g) == fa.map(f.andThen(g))
  * ```
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  *
  * @tparam X
  *   The type of value contained or produced (e.g. [[Int]]).
  */
trait Functor[+Self[+_], +X]
  extends BoundedFunctor[Self, Any, X],
          ContextFunctor[Self, NoContext, X],
          WhenOps[
            Self,
            Any,
            FunctorConditionalView[Self, X],
            X,
          ],
          DeepOps[Self, X]:

  override final inline def when
    (condition: X => Boolean)
    : FunctorConditionalView[Self, X] = FunctorConditionalView(this, condition)

  override final inline def when[Active : ClassTag]
    : FunctorTypeView[Self, X, Active] =
    new FunctorTypeView[Self, X, Active](this)

  override final inline def unless[Inactive : ClassTag]
    : FunctorNegatedTypeView[Self, X, Inactive] =
    new FunctorNegatedTypeView[Self, X, Inactive](this)

  override final inline def deep[Inner[+_ <: C], C, Ctx[_ <: C], Y <: C]
    (
      using X <:< PartialFunctor[Inner, C, Ctx, Y],
      (Inner[C] | PartialFunctor[Inner, C, Ctx, Y]) <:< Any,
    )
    : DeepFunctorView[Inner, C, Ctx, Y, Self, Any] =
    DeepFunctorView(asInstanceOf[BoundedFunctor[
      Self,
      Any,
      PartialFunctor[Inner, C, Ctx, Y],
    ]])

object Functor:

  /** A [[Functor]] that never contains any value. */
  trait Empty[+Self : ValueOf] extends Functor[[_] =>> Self, Nothing]:

    override inline def map[Y : NoContext](transform: Nothing => Y): Self =
      valueOf[Self]
