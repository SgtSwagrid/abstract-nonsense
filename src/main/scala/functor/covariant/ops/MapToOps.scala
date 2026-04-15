package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import io.github.sgtswagrid.nonsense.functor.covariant.ContextFunctor

/** The [[mapTo]] operator for [[ContextFunctor]], and its derivatives. */
trait MapToOps[
  +Self[+_],
  -Codomain,
  -Context[_ <: Codomain],
  +X <: Codomain,
] extends MapOps[Self, Codomain, Context, X]:

  /** Ignore the existing value and take a new [[value]] instead. */
  final def mapTo[Y <: Codomain : Context](value: Y): Self[Y] = map(_ => value)

  /**
    * Ignore the existing value and take a new [[value]] instead.
    *
    * @note
    *   [[value]] is evaluated at most once, upon first use.
    */
  final def mapToLazy[Y <: Codomain : Context](value: => Y): Self[Y] =
    lazy val cache = value
    map(_ => cache)

  /**
    * Ignore the existing value and take a new [[value]] instead.
    *
    * @note
    *   [[value]] is re-evaluated upon every use.
    */
  final def mapToGenerator[Y <: Codomain : Context](value: => Y): Self[Y] =
    map(_ => value)
