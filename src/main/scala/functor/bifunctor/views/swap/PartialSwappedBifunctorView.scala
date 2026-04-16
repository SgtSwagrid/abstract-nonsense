package io.github.sgtswagrid.nonsense
package functor.bifunctor.views.swap

import io.github.sgtswagrid.nonsense.functor.bifunctor.PartialBifunctor

/** A view of a [[PartialBifunctor]] with the left and right sides swapped. */
trait PartialSwappedBifunctorView[
  +Self[+_, +_],
  -LeftCodomain,
  -RightCodomain,
  -Context[_, _],
  +L <: LeftCodomain,
  +R <: RightCodomain,
] extends PartialBifunctor[
    [l, r] =>> Self[r, l],
    LeftCodomain,
    RightCodomain,
    Context,
    L,
    R,
  ]:

  protected def base
    : PartialBifunctor[
      Self,
      RightCodomain,
      LeftCodomain,
      [l, r] =>> Context[r, l],
      R,
      L,
    ]

  override def bimap[l <: LeftCodomain, r <: RightCodomain]
    (using Context[l, r])
    (left: L => l)
    (right: R => r)
    : Self[r, l] = base.bimap[r, l](right)(left)

object PartialSwappedBifunctorView:

  def apply[
    Self[+_, +_],
    LeftCodomain,
    RightCodomain,
    Context[_, _],
    L <: LeftCodomain,
    R <: RightCodomain,
  ]
    (
      b: PartialBifunctor[
        Self,
        LeftCodomain,
        RightCodomain,
        Context,
        L,
        R,
      ],
    )
    : PartialSwappedBifunctorView[
      Self,
      RightCodomain,
      LeftCodomain,
      [l, r] =>> Context[r, l],
      R,
      L,
    ] = new PartialSwappedBifunctorView[
    Self,
    RightCodomain,
    LeftCodomain,
    [l, r] =>> Context[r, l],
    R,
    L,
  ]:
    override protected val base = b
