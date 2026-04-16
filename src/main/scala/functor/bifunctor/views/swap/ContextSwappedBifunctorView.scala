package io.github.sgtswagrid.nonsense
package functor.bifunctor.views.swap

import io.github.sgtswagrid.nonsense.functor.bifunctor.ContextBifunctor

/** A view of a [[ContextBifunctor]] with the left and right sides swapped. */
trait ContextSwappedBifunctorView[+Self[+_, +_], -Context[_], +L, +R]
  extends PartialSwappedBifunctorView[Self, Any, Any, [l,
  r] =>> Context[l | r], L, R],
          ContextBifunctor[[l, r] =>> Self[r, l], Context, L, R]

object ContextSwappedBifunctorView:

  def apply[Self[+_, +_], Context[_], L, R]
    (b: ContextBifunctor[Self, Context, L, R])
    : ContextSwappedBifunctorView[Self, Context, R, L] =
    new ContextSwappedBifunctorView[Self, Context, R, L]:
      override protected val base = b
