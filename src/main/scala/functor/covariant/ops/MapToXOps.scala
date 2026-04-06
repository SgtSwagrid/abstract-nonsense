package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import scala.reflect.ClassTag
import scala.util.{Failure, Success}

/** Type-specific variants of [[mapTo]]. */
trait MapToXOps[+Self[+_], +Content, -Codomain]
  extends MapToOps[Self, Content, Codomain]:

  /** Alias for `this.mapTo(None)`. */
  final inline def mapToNone(using None.type <:< Codomain): Self[None.type] =
    mapToWithEvidence(None)

  /** Alias for `this.map(Some.apply)`. */
  final inline def mapToSome
    (using Some[Content] <:< Codomain)
    : Self[Some[Content]] = mapWithEvidence(Some.apply)

  /** Alias for `this.map(value => Option.when(condition(value))(value))`. */
  final inline def mapToOption
    (using Option[Content] <:< Codomain)
    (condition: Content => Boolean)
    : Self[Option[Content]] =
    mapWithEvidence(value => Option.when(condition(value))(value))

  final inline def mapToOption[Type <: Codomain : ClassTag]
    (using Option[Type] <:< Codomain)
    : Self[Option[Type]] = mapWithEvidence:
    case value: Type => Some(value)
    case value       => None

  /** Alias for `this.map(Left.apply)`. */
  final inline def mapToLeft
    (using Left[Content, Nothing] <:< Codomain)
    : Self[Left[Content, Nothing]] = mapWithEvidence(Left.apply)

  /** Alias for `this.map(Right.apply)`. */
  final inline def mapToRight
    (using Right[Nothing, Content] <:< Codomain)
    : Self[Right[Nothing, Content]] = mapWithEvidence(Right.apply)

  /** Alias for `this.map(Success.apply)`. */
  final inline def mapToSuccess
    (using Success[Content] <:< Codomain)
    : Self[Success[Content]] = mapWithEvidence(Success.apply)

  /** Alias for `this.map(Failure.apply)`. */
  final inline def mapToFailure[Error >: Content]
    (
      using Failure[Error] <:< Codomain,
      Content <:< Throwable,
    )
    : Self[Failure[Error]] = mapWithEvidence(Failure.apply)

  /** Alias for `this.mapTo(())`. */
  final inline def mapToUnit(using Unit <:< Codomain): Self[Unit] =
    mapToWithEvidence(())

  /** Alias for `this.mapTo(true)`. */
  final inline def mapToTrue(using true <:< Codomain): Self[true] =
    mapToWithEvidence(true)

  /** Alias for `this.mapTo(false)`. */
  final inline def mapToFalse(using false <:< Codomain): Self[false] =
    mapToWithEvidence(false)

  /** Alias for `this.mapTo(0)`. */
  final inline def mapToZero[Number >: Content <: Codomain : Numeric as Number]
    : Self[Number] = mapToWithEvidence(Number.zero)

  /** Alias for `this.mapTo(1)`. */
  final inline def mapToOne[Number >: Content <: Codomain : Numeric as Number]
    : Self[Number] = mapToWithEvidence(Number.one)
