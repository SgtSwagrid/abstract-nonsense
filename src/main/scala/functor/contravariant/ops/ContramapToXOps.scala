package io.github.sgtswagrid.nonsense
package functor.contravariant.ops

import scala.util.{Failure, Success}

/** Type-specific variants of [[contramapTo]] and [[contramap]]. */
trait ContramapToXOps[
  +Self[-_],
  +Domain,
  -Context[_],
  -X >: Domain,
] extends ContramapToOps[Self, Domain, Context, X]:

  /** Alias for `this.contramapTo(None)`. */
  final inline def contramapToNone[Y >: Domain : Context]
    (using cast: None.type <:< X)
    : Self[Y] = contramapTo[Y](cast(None))

  /** Alias for `this.contramap(y => castX(Some(castY(y))))`. */
  final inline def contramapToSome[Y >: Domain : Context]
    (using cast: Some[Y] <:< X)
    : Self[Y] = contramap[Y](y => cast(Some(y)))

  /** Alias for `this.contramap(cast(_).value)`. */
  final inline def contramapFromSome[Y >: Domain : Context]
    (using cast: Y <:< Some[X])
    : Self[Y] = contramap[Y](cast(_).value)

  /** Alias for `this.contramap(cast(_).getOrElse(default))`. */
  final inline def contramapFromOption[Y >: Domain : Context]
    (default: => X)
    (using cast: Y <:< Option[X])
    : Self[Y] = contramap[Y](cast(_).getOrElse(default))

  /** Alias for `this.contramap(y => castX(Left(castY(y))))`. */
  final inline def contramapToLeft[Y >: Domain : Context, Z]
    (using cast: Left[Y, Z] <:< X)
    : Self[Y] = contramap[Y](y => cast(Left(y)))

  /** Alias for `this.contramap(cast(_).value)`. */
  final inline def contramapFromLeft[Y >: Domain : Context, Z]
    (using cast: Y <:< Left[X, Z])
    : Self[Y] = contramap[Y](cast(_).value)

  /** Alias for `this.contramap(y => castX(Right(castY(y))))`. */
  final inline def contramapToRight[Y >: Domain : Context, Z]
    (using cast: Right[Z, Y] <:< X)
    : Self[Y] = contramap[Y](y => cast(Right(y)))

  /** Alias for `this.contramap(cast(_).value)`. */
  final inline def contramapFromRight[Y >: Domain : Context, Z]
    (using cast: Y <:< Right[Z, X])
    : Self[Y] = contramap[Y](cast(_).value)

  /** Alias for `this.contramap(y => castX(Success(y)))`. */
  final inline def contramapToSuccess[Y >: Domain : Context]
    (using cast: Success[Y] <:< X)
    : Self[Y] = contramap[Y](y => cast(Success(y)))

  /** Alias for `this.contramap(cast(_).value)`. */
  final inline def contramapFromSuccess[Y >: Domain : Context]
    (using cast: Y <:< Success[X])
    : Self[Y] = contramap[Y](cast(_).value)

  /** Alias for `this.contramap(y => castX(Failure[Y](castY(y))))`. */
  final inline def contramapToFailure[Y >: Domain : Context]
    (
      using castY: Y <:< Throwable,
      castX: Failure[Y] <:< X,
    )
    : Self[Y] = contramap[Y](y => castX(Failure[Y](castY(y))))

  /** Alias for `this.contramap(castY(_).exception)`. */
  final inline def contramapFromFailure[T, Y >: Domain : Context]
    (
      using castY: Y <:< Failure[T],
      castX: Throwable <:< X,
    )
    : Self[Y] = contramap[Y](y => castX(castY(y).exception))

  /** Alias for `this.contramapTo(())`. */
  final inline def contramapToUnit[Y >: Domain : Context]
    (using cast: Unit <:< X)
    : Self[Y] = contramapTo[Y](cast(()))

  /** Alias for `this.contramapTo(true)`. */
  final inline def contramapToTrue[Y >: Domain : Context]
    (using cast: true <:< X)
    : Self[Y] = contramapTo[Y](cast(true))

  /** Alias for `this.contramapTo(false)`. */
  final inline def contramapToFalse[Y >: Domain : Context]
    (using cast: false <:< X)
    : Self[Y] = contramapTo[Y](cast(false))
