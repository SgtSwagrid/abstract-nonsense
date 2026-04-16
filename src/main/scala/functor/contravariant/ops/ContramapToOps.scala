package io.github.sgtswagrid.nonsense
package functor.contravariant.ops

/** The [[contramapTo]] operator for [[PartialContravariant]], and its derivatives. */
trait ContramapToOps[
  +Self[-_],
  +Domain,
  -Context[_],
  -X >: Domain,
] extends ContramapOps[Self, Domain, Context, X]:

  /** Ignore the input and always pass [[value]] to this consumer. */
  final def contramapTo[Y >: Domain : Context](value: X): Self[Y] =
    contramap[Y](_ => value)

  /**
    * Ignore the input and always pass [[value]] to this consumer.
    *
    * @note
    *   [[value]] is evaluated at most once, upon first use.
    */
  final def contramapToLazy[Y >: Domain : Context](value: => X): Self[Y] =
    lazy val cache = value
    contramap[Y](_ => cache)

  /**
    * Ignore the input and always pass [[value]] to this consumer.
    *
    * @note
    *   [[value]] is re-evaluated upon every use.
    */
  final def contramapToGenerator[Y >: Domain : Context](value: => X): Self[Y] =
    contramap[Y](_ => value)
