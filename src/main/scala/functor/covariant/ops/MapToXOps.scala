package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import scala.reflect.ClassTag
import scala.util.{Failure, Success}

/** Type-specific variants of [[mapTo]]. */
trait MapToXOps[
  +Self[+_],
  -Codomain,
  -Context[_],
  +Output <: Codomain,
] extends MapToOps[Self, Codomain, Context, Output]:

  /** Alias for `this.mapTo(None)`. */
  final inline def mapToNone[Post >: None.type <: Codomain : Context]
    : Self[Post] = mapTo[Post](None)

  /** Alias for `this.map(Some.apply)`. */
  final inline def mapToSome[Post >: Some[Output] <: Codomain : Context]
    : Self[Post] = map[Post](Some.apply)

  /** Alias for `this.map(value => Option.when(condition(value))(value))`. */
  final inline def mapToOption[Post >: Option[Output] <: Codomain : Context]
    (condition: Output => Boolean)
    : Self[Post] = map[Post](value => Option.when(condition(value))(value))

  /*final inline def mapToOption[Active <: Codomain : ClassTag]
    (
      using Option[Active] <:< Codomain,
      Context[Option[Active]],
    )
    : Self[Option[Active]] = map:
    case value: Active => Some(value)
    case value         => None*/

  /** Alias for `this.map(Left.apply)`. */
  final inline def mapToLeft[
    Post >: Left[Output, Nothing] <: Codomain : Context,
  ]: Self[Post] = map[Post](Left.apply)

  /** Alias for `this.map(Right.apply)`. */
  final inline def mapToRight[
    Post >: Right[Nothing, Output] <: Codomain : Context,
  ]: Self[Post] = map[Post](Right.apply)

  /** Alias for `this.map(Success.apply)`. */
  final inline def mapToSuccess[Post >: Success[Output] <: Codomain : Context]
    : Self[Post] = map[Post](Success.apply)

  /** Alias for `this.map(Failure.apply)`. */
  /*final inline def mapToFailure[Post >: Failure[Output] <: Codomain : Context]
    : Self[Post] = map[Post](Failure.apply)*/

  /** Alias for `this.mapTo(())`. */
  final inline def mapToUnit[Post >: Unit <: Codomain : Context]: Self[Post] =
    mapTo[Post](())

  /** Alias for `this.mapTo(true)`. */
  final inline def mapToTrue[Post >: true <: Codomain : Context]: Self[Post] =
    mapTo[Post](true)

  /** Alias for `this.mapTo(false)`. */
  final inline def mapToFalse[Post >: false <: Codomain : Context]: Self[Post] =
    mapTo[Post](false)

  /** Alias for `this.mapTo(0)`. */
  final inline def mapToZero[
    Number >: Output <: Codomain : {Context, Numeric as Number},
  ]: Self[Number] = mapTo(Number.zero)

  /** Alias for `this.mapTo(1)`. */
  final inline def mapToOne[
    Number >: Output <: Codomain : {Context, Numeric as Number},
  ]: Self[Number] = mapTo(Number.one)
