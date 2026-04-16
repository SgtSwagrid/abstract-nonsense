package io.github.sgtswagrid.nonsense
package functor.invariant.ops

import scala.util.Success

/**
  * Type-specific variants of [[xmap]], applicable when a natural isomorphism is
  * available.
  */
trait XmapToXOps[
  +Self[_],
  +Domain,
  -Codomain,
  -Context[_],
  X >: Domain <: Codomain,
] extends XmapToOps[Self, Domain, Codomain, Context, X]:

  /** Alias for `this.xmap(Some.apply)(cast(_).value)`. */
  final inline def xmapToSome[Y >: (Some[X] | Domain) <: Codomain : Context]
    (using cast: Y <:< Some[X])
    : Self[Y] = xmap[Y](Some.apply)(cast(_).value)

  /** Alias for `this.xmap(cast(_).value)(y => cast.flip(Some(y)))`. */
  final inline def xmapFromSome[Y >: Domain <: Codomain : Context]
    (using cast: X =:= Some[Y])
    : Self[Y] = xmap[Y](cast(_).value)(y => cast.flip(Some(y)))

  /** Alias for `this.xmap(Left.apply)(cast(_).value)`. */
  final inline def xmapToLeft[
    Y >: (Left[X, Nothing] | Domain) <: Codomain : Context,
    Z,
  ](using cast: Y <:< Left[X, Z]): Self[Y] = xmap[Y](Left.apply)(cast(_).value)

  /** Alias for `this.xmap(cast(_).value)(y => cast.flip(Left(y)))`. */
  final inline def xmapFromLeft[Y >: Domain <: Codomain : Context, Z]
    (using cast: X =:= Left[Y, Z])
    : Self[Y] = xmap[Y](cast(_).value)(y => cast.flip(Left(y)))

  /** Alias for `this.xmap(Right.apply)(cast(_).value)`. */
  final inline def xmapToRight[
    Y >: (Right[Nothing, X] | Domain) <: Codomain : Context,
    Z,
  ](using cast: Y <:< Right[Z, X]): Self[Y] =
    xmap[Y](Right.apply)(cast(_).value)

  /** Alias for `this.xmap(cast(_).value)(y => cast.flip(Right(y)))`. */
  final inline def xmapFromRight[Y >: Domain <: Codomain : Context, Z]
    (using cast: X =:= Right[Z, Y])
    : Self[Y] = xmap[Y](cast(_).value)(y => cast.flip(Right(y)))

  /** Alias for `this.xmap(Success.apply)(cast(_).value)`. */
  final inline def xmapToSuccess[
    Y >: (Success[X] | Domain) <: Codomain : Context,
  ](using cast: Y <:< Success[X]): Self[Y] =
    xmap[Y](Success.apply)(cast(_).value)

  /** Alias for `this.xmap(cast(_).value)(y => cast.flip(Success(y)))`. */
  final inline def xmapFromSuccess[Y >: Domain <: Codomain : Context]
    (using cast: X =:= Success[Y])
    : Self[Y] = xmap[Y](cast(_).value)(y => cast.flip(Success(y)))
