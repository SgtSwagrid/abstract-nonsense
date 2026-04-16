package io.github.sgtswagrid.nonsense
package functor.bifunctor

import io.github.sgtswagrid.nonsense.functor.bifunctor.views.BifunctorLeftView

/**
  * ## Right-Bounded Bifunctors
  *
  * A right-bounded bifunctor is a restricted [[Bifunctor]] that can only
  * contain values with certain properties on the right, but any value on the
  * left.
  *
  * ### Constraints
  *
  *   1. An upper bound [[RightCodomain]] on the type [[R]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam RightCodomain
  *   The upper bound on [[R]] (e.g. [[Any]]).
  *
  * @tparam L
  *   The type of value contained on the left-hand side (e.g. [[Int]]).
  *
  * @tparam R
  *   The type of value contained on the right-hand side (e.g. [[Int]]).
  */
trait RightBoundedBifunctor[
  +Self[+_, +_],
  -RightCodomain,
  +L,
  +R <: RightCodomain,
] extends BoundedBifunctor[Self, Any, RightCodomain, L, R]:

  override final inline def left: BifunctorLeftView[Self, L, R] =
    BifunctorLeftView(this)

object RightBoundedBifunctor:

  /** A [[RightBoundedBifunctor]] that never contains any value. */
  trait Empty[+Self : ValueOf, -RightCodomain]
    extends RightBoundedBifunctor[
      [_, _] =>> Self,
      RightCodomain,
      Nothing,
      Nothing,
    ]:

    override protected final def bimapImpl[l, r <: RightCodomain]
      (left: Nothing => l)
      (right: Nothing => r)
      : Self = valueOf[Self]
