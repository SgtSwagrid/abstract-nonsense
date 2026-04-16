package io.github.sgtswagrid.nonsense
package functor.bifunctor

import io.github.sgtswagrid.nonsense.functor.covariant.{BoundedFunctor, Functor}
import io.github.sgtswagrid.nonsense.functor.covariant.PartialFunctor.NoContext
import io.github.sgtswagrid.nonsense.functor.profunctor.Profunctor

/**
  * ## Bifunctor
  *
  * A Bifunctor is an object with a [[bimap]] operator.
  *
  * They typically represent structures that contain or produce two distinct
  * kinds of value.
  *
  * ### Bifunctor Laws
  *
  * Please see the corresponding covariant functor laws for [[Functor]]. They
  * should hold here too.
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam L
  *   The type of value contained on the left-hand side (e.g. [[Int]]).
  *
  * @tparam R
  *   The type of value contained on the right-hand side (e.g. [[Int]]).
  *
  * @see
  *   [[Profunctor]] is contravariant in one of the type parameters.
  */
trait Bifunctor[+Self[+_, +_], +L, +R]
  extends LeftBoundedBifunctor[Self, Any, L, R],
          RightBoundedBifunctor[Self, Any, L, R],
          ContextBifunctor[Self, NoContext, L, R]

object Bifunctor:

  /** A [[Bifunctor]] that never contains any value. */
  trait Empty[+Self : ValueOf]
    extends Bifunctor[[_, _] =>> Self, Nothing, Nothing]:

    override protected final def bimapImpl[l, r]
      (left: Nothing => l)
      (right: Nothing => r)
      : Self = valueOf[Self]
