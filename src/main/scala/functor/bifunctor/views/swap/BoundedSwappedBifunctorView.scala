package io.github.sgtswagrid.nonsense
package functor.bifunctor.views.swap

import io.github.sgtswagrid.nonsense.functor.bifunctor.BoundedBifunctor

/** A view of a [[BoundedBifunctor]] with the left and right sides swapped. */
trait BoundedSwappedBifunctorView[
  +Self[+_, +_],
  -LeftCodomain,
  -RightCodomain,
  +L <: LeftCodomain,
  +R <: RightCodomain,
] extends PartialSwappedBifunctorView[
    Self,
    LeftCodomain,
    RightCodomain,
    [l, r] =>> DummyImplicit,
    L,
    R,
  ],
          BoundedBifunctor[
            [l, r] =>> Self[r, l],
            LeftCodomain,
            RightCodomain,
            L,
            R,
          ]

object BoundedSwappedBifunctorView:

  def apply[
    Self[+_, +_],
    LeftCodomain,
    RightCodomain,
    L <: LeftCodomain,
    R <: RightCodomain,
  ]
    (b: BoundedBifunctor[Self, LeftCodomain, RightCodomain, L, R])
    : BoundedSwappedBifunctorView[Self, RightCodomain, LeftCodomain, R, L] =
    new BoundedSwappedBifunctorView[Self, RightCodomain, LeftCodomain, R, L]:
      override protected val base = b
