package io.github.sgtswagrid.nonsense
package functor.covariant

import io.github.sgtswagrid.nonsense.functor.covariant.PartialFunctor.NoContext
import io.github.sgtswagrid.nonsense.functor.covariant.ops.DeepOps
import io.github.sgtswagrid.nonsense.functor.covariant.views.DeepFunctorView

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
          DeepOps[Self, X]:

  override final inline def deep[Inner[+_], Y]
    (using X <:< Functor[Inner, Y])
    : DeepFunctorView[Self, Inner, Y] =
    DeepFunctorView(asInstanceOf[Functor[Self, Functor[Inner, Y]]])

object Functor:

  /** A [[Functor]] that never contains any value. */
  trait Empty[+Self : ValueOf] extends Functor[[_] =>> Self, Nothing]:

    override protected inline def mapImpl[Y](transform: Nothing => Y): Self =
      valueOf[Self]
