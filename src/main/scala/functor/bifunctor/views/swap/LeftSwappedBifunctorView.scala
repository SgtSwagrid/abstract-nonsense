package io.github.sgtswagrid.nonsense
package functor.bifunctor.views.swap

import io.github.sgtswagrid.nonsense.functor.bifunctor.{
  LeftBoundedBifunctor, RightBoundedBifunctor,
}

/** A view of a [[LeftBoundedBifunctor]] with the left and right sides swapped. */
trait LeftSwappedBifunctorView[
  +Self[+_, +_],
  -Codomain,
  +L,
  +R <: Codomain,
] extends BoundedSwappedBifunctorView[Self, Any, Codomain, L, R],
          RightBoundedBifunctor[[l, r] =>> Self[r, l], Codomain, L, R]

object LeftSwappedBifunctorView:

  def apply[Self[+_, +_], Codomain, L, R <: Codomain]
    (b: LeftBoundedBifunctor[Self, Codomain, R, L])
    : LeftSwappedBifunctorView[Self, Codomain, L, R] =
    new LeftSwappedBifunctorView[Self, Codomain, L, R]:
      override protected val base = b
