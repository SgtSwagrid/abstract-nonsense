package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import scala.reflect.ClassTag
import scala.util.{Failure, Success}

/** Type-specific variants of [[mapTo]]. */
trait MapToXOps[
  +Self[+_],
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

  /** Alias for `this.map(value => Option.when(condition(value))(value))`. */
  final inline def mapToOption[Y >: Option[X] <: Codomain : Context]
    (condition: X => Boolean)
    : Self[Y] = map[Y](value => Option.when(condition(value))(value))

  /*final inline def mapToOption[Active <: Codomain : ClassTag]
    (
      using Option[Active] <:< Codomain,
      Context[Option[Active]],
    )
    : Self[Option[Active]] = map:
    case value: Active => Some(value)
    case value         => None*/

  /** Alias for `this.map(Left.apply)`. */
  final inline def mapToLeft[Y >: Left[X, Nothing] <: Codomain : Context]
    : Self[Y] = map[Y](Left.apply)

  /** Alias for `this.map(Right.apply)`. */
  final inline def mapToRight[Y >: Right[Nothing, X] <: Codomain : Context]
    : Self[Y] = map[Y](Right.apply)

  /** Alias for `this.map(Success.apply)`. */
  final inline def mapToSuccess[Y >: Success[X] <: Codomain : Context]
    : Self[Y] = map[Y](Success.apply)

  /** Alias for `this.map(Failure.apply)`. */
  /*final inline def mapToFailure[Y >: Failure[Output] <: Codomain : Context]
    : Self[Y] = map[Y](Failure.apply)*/

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
