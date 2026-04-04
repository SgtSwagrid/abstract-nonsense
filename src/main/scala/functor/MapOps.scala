package io.github.sgtswagrid.nonsense
package functor

import io.github.sgtswagrid.nonsense.caching.Cache

/** The [[map]] operator for [[BoundedFunctorOps]], and its derivatives. */
trait MapOps[+Self[+_], +Content, -Codomain]:

  /** Transform the contents arbitrarily. */
  def map[Result <: Codomain](transform: Content => Result): Self[Result]

  /**
    * A version of [[map]] where the input to [[transform]] is provided
    * implicitly as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  final def mapCtx[Result <: Codomain]
    (transform: Content ?=> Result)
    : Self[Result] = map(value => transform(using value))

  /**
    * A version of [[map]] where [[transform]] is cached so as to prevent
    * repeated computation for the same input.
    *
    * @note
    *   All outputs of [[transform]] will be kept in memory until the returned
    *   structure is garbage-collected.
    */
  final inline def mapWithCache[Result <: Codomain]
    (transform: Content => Result)
    : Self[Result] = map(Cache.memoise(transform))

  /**
    * Performs a side effect for each contained value.
    *
    * @note
    *   Equivalent to `this.map{ value => effect(value); value }`.
    */
  final inline def forEach
    (using Content <:< Codomain)
    (effect: Content => Any)
    : Self[Content] = map: value =>
    effect(value)
    value.asInstanceOf[Content & Codomain]

  /**
    * Casts each element of this structure to type [[Result]].
    *
    * @note
    *   Equivalent to, but more efficient than
    *   `this.map(_.asInstanceOf[Result])`.
    */
  final inline def eachAsInstanceOf[Result <: Codomain]: Self[Result] =
    asInstanceOf[Self[Result]]
