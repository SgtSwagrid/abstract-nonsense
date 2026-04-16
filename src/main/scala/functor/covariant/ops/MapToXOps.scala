package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import scala.reflect.ClassTag
import scala.util.{Failure, Success}

/** Type-specific variants of [[mapTo]] and [[map]]. */
trait MapToXOps[
  +Self[+_ <: Codomain],
  -Codomain,
  -Context[_ <: Codomain],
  +X <: Codomain,
] extends MapToOps[Self, Codomain, Context, X]:

  /** Alias for `this.mapTo(None)`. */
  final inline def mapToNone[Y >: None.type <: Codomain : Context]: Self[Y] =
    mapTo[Y](None)

  /** Alias for `this.map(Some.apply)`. */
  final inline def mapToSome[Y >: Some[X] <: Codomain : Context]: Self[Y] =
    map[Y](Some.apply)

  /** Alias for `this.map(cast(_).value)`. */
  final inline def mapFromSome[Y <: Codomain : Context]
    (using cast: X <:< Some[Y])
    : Self[Y] = map[Y](cast(_).value)

  /** Alias for `this.map(value => Option.when(condition(value))(value))`. */
  final inline def mapToOption[Y >: Option[X] <: Codomain : Context]
    (condition: X => Boolean)
    : Self[Y] = map[Y](value => Option.when(condition(value))(value))

  /**
    * Maps each value to `Some` if it is an instance of [[Active]], or `None`
    * otherwise.
    */
  final inline def mapToOption[
    Active <: Codomain,
    Y >: Option[Active] <: Codomain : Context,
  ](using ClassTag[Active]): Self[Y] = map[Y]: v =>
    summon[ClassTag[Active]].unapply(v) match
      case Some(a) => Some(a)
      case None    => None

  /** Alias for `this.map(cast(_).getOrElse(default))`. */
  final inline def mapFromOption[Y <: Codomain : Context]
    (default: => Y)
    (using cast: X <:< Option[Y])
    : Self[Y] = map[Y](cast(_).getOrElse(default))

  /** Alias for `this.map(Left.apply)`. */
  final inline def mapToLeft[Y >: Left[X, Nothing] <: Codomain : Context]
    : Self[Y] = map[Y](Left.apply)

  /** Alias for `this.map(cast(_).value)`. */
  final inline def mapFromLeft[Y <: Codomain : Context, Z]
    (using cast: X <:< Left[Y, Z])
    : Self[Y] = map[Y](cast(_).value)

  /** Alias for `this.map(Right.apply)`. */
  final inline def mapToRight[Y >: Right[Nothing, X] <: Codomain : Context]
    : Self[Y] = map[Y](Right.apply)

  /** Alias for `this.map(cast(_).value)`. */
  final inline def mapFromRight[Y <: Codomain : Context, Z]
    (using cast: X <:< Right[Z, Y])
    : Self[Y] = map[Y](cast(_).value)

  /** Alias for `this.map(Success.apply)`. */
  final inline def mapToSuccess[Y >: Success[X] <: Codomain : Context]
    : Self[Y] = map[Y](Success.apply)

  /** Alias for `this.map(cast(_).value)`. */
  final inline def mapFromSuccess[Y <: Codomain : Context]
    (using cast: X <:< Success[Y])
    : Self[Y] = map[Y](cast(_).value)

  /** Alias for `this.map(x => Failure[X](cast(x)))`. */
  final inline def mapToFailure[Y >: Failure[X] <: Codomain : Context]
    (using cast: X <:< Throwable)
    : Self[Y] = map[Y](x => Failure[X](cast(x)))

  /** Alias for `this.map(cast(_).exception)`. */
  final inline def mapFromFailure[T, Y >: Throwable <: Codomain : Context]
    (using cast: X <:< Failure[T])
    : Self[Y] = map[Y](cast(_).exception)

  /** Alias for `this.mapTo(())`. */
  final inline def mapToUnit[Y >: Unit <: Codomain : Context]: Self[Y] =
    mapTo[Y](())

  /** Alias for `this.mapTo(true)`. */
  final inline def mapToTrue[Y >: true <: Codomain : Context]: Self[Y] =
    mapTo[Y](true)

  /** Alias for `this.mapTo(false)`. */
  final inline def mapToFalse[Y >: false <: Codomain : Context]: Self[Y] =
    mapTo[Y](false)

  /** Alias for `this.mapTo(0)`. */
  final inline def mapToZero[
    Number >: X <: Codomain : {Context, Numeric as Number},
  ]: Self[Number] = mapTo(Number.zero)

  /** Alias for `this.mapTo(1)`. */
  final inline def mapToOne[
    Number >: X <: Codomain : {Context, Numeric as Number},
  ]: Self[Number] = mapTo(Number.one)
