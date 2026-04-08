package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor

/** The [[mapTo]] operator for [[BoundedFunctor]], and its derivatives. */
trait MapToOps[+Self[+_], -Codomain, +Output]
  extends MapOps[Self, Codomain, Output]:

  /** Ignore the existing value and take a new [[value]] instead. */
  final def mapTo[Post <: Codomain](value: Post): Self[Post] = map(_ => value)

  /**
    * Ignore the existing value and take a new [[value]] instead.
    *
    * @note
    *   [[value]] is evaluated at most once, upon first use.
    */
  final def mapToLazy[Post <: Codomain](value: => Post): Self[Post] =
    lazy val cache = value
    map(_ => cache)

  /**
    * Ignore the existing value and take a new [[value]] instead.
    *
    * @note
    *   [[value]] is re-evaluated upon every use.
    */
  final def mapToGenerator[Post <: Codomain](value: => Post): Self[Post] =
    map(_ => value)
