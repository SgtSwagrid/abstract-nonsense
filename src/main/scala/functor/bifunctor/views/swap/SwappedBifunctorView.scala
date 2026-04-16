package io.github.sgtswagrid.nonsense
package functor.bifunctor.views.swap

import io.github.sgtswagrid.nonsense.functor.bifunctor.Bifunctor
import io.github.sgtswagrid.nonsense.util.NoContext
import scala.annotation.unchecked.uncheckedVariance

/** A view of a [[Bifunctor]] with the left and right sides swapped. */
class SwappedBifunctorView[+Self[+_, +_], +L, +R]
  (
    b: Bifunctor[
      Self,
      R @uncheckedVariance,
      L @uncheckedVariance,
    ],
  )
  extends BoundedSwappedBifunctorView[Self, Any, Any, L, R],
          ContextSwappedBifunctorView[Self, NoContext, L, R],
          LeftSwappedBifunctorView[Self, Any, L, R],
          RightSwappedBifunctorView[Self, Any, L, R],
          Bifunctor[[l, r] =>> Self[r, l], L, R]:

  override protected val base
    : Bifunctor[
      Self,
      R @uncheckedVariance,
      L @uncheckedVariance,
    ] = b

  override inline def bimap[l, r]
    (using DummyImplicit)
    (left: L => l)
    (right: R => r)
    : Self[r, l] = base.bimap[r, l](right)(left)
