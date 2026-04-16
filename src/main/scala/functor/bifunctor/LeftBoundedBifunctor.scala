package io.github.sgtswagrid.nonsense
package functor.bifunctor

import io.github.sgtswagrid.nonsense.functor.bifunctor.views.projection.BifunctorRightView
import io.github.sgtswagrid.nonsense.functor.bifunctor.views.swap.LeftSwappedBifunctorView

/**
  * ## Left-Bounded Bifunctors
  *
  * A left-bounded bifunctor is a restricted [[Bifunctor]] that can only contain
  * values with certain properties on the left, but any value on the right.
  *
  * ### Constraints
  *
  *   1. An upper bound [[LeftCodomain]] on the type [[L]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam LeftCodomain
  *   The upper bound on [[L]] (e.g. [[Any]]).
  *
  * @tparam L
  *   The type of value contained on the left-hand side (e.g. [[Int]]).
  *
  * @tparam R
  *   The type of value contained on the right-hand side (e.g. [[Int]]).
  */
trait LeftBoundedBifunctor[
  +Self[+_, +_],
  -LeftCodomain,
  +L <: LeftCodomain,
  +R,
] extends BoundedBifunctor[Self, LeftCodomain, Any, L, R]:

  override def swap: LeftSwappedBifunctorView[Self, LeftCodomain, R, L] =
    LeftSwappedBifunctorView(this)

  override final inline def right: BifunctorRightView[Self, L, R] =
    BifunctorRightView(this)

object LeftBoundedBifunctor:

  /** A [[LeftBoundedBifunctor]] that never contains any value. */
  trait Empty[+Self : ValueOf, -LeftCodomain]
    extends LeftBoundedBifunctor[
      [_, _] =>> Self,
      LeftCodomain,
      Nothing,
      Nothing,
    ]:

    override def bimap[l <: LeftCodomain, r]
      (using DummyImplicit)
      (transformLeft: Nothing => l)
      (transformRight: Nothing => r)
      : Self = valueOf[Self]
