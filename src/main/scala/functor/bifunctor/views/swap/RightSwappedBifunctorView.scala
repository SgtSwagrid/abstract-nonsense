package io.github.sgtswagrid.nonsense
package functor.bifunctor.views.swap

import io.github.sgtswagrid.nonsense.functor.bifunctor.{
  LeftBoundedBifunctor, RightBoundedBifunctor,
}

/** A view of a [[RightBoundedBifunctor]] with the left and right sides swapped. */
trait RightSwappedBifunctorView[
  +Self[+_, +_],
  -Codomain,
  +L <: Codomain,
  +R,
] extends BoundedSwappedBifunctorView[Self, Codomain, Any, L, R],
          LeftBoundedBifunctor[[l, r] =>> Self[r, l], Codomain, L, R]

object RightSwappedBifunctorView:

  def apply[Self[+_, +_], Codomain, L <: Codomain, R]
    (b: RightBoundedBifunctor[Self, Codomain, R, L])
    : RightSwappedBifunctorView[Self, Codomain, L, R] =
    new RightSwappedBifunctorView[Self, Codomain, L, R]:
      override protected val base = b
