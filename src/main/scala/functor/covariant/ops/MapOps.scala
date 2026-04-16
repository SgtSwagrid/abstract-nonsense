package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import io.github.sgtswagrid.nonsense.caching.Cache
import io.github.sgtswagrid.nonsense.functor.covariant.Functor

/** The [[map]] operator for [[BoundedFunctor]], and its derivatives. */
trait MapOps[
  +Self[+_ <: Codomain],
  -Codomain,
  -Context[_ <: Codomain],
  +X <: Codomain,
]:

  /**
    * ## `map` (from [[Functor]])
    *
    * Transforms the contents or output of this structure.
    *
    * @param transform
    *   An arbitrary mapping applied to each value.
    *
    * @return
    *   A projected version of this structure, leaving this original unchanged.
    *
    * @example
    *   ```scala
    *   val a = List(1, 2, 3, 4)
    *   val b = a.map(_ * 2)
    *   // b == List(2, 4, 6, 8)
    *   ```
    */
  def map[Y <: Codomain : Context](transform: X => Y): Self[Y]

  /**
    * A version of [[map]] where the input to [[transform]] is provided
    * implicitly as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  final inline def mapWithContext[Y <: Codomain : Context]
    (transform: X ?=> Y)
    : Self[Y] = map(value => transform(using value))

  /**
    * A version of [[map]] where [[transform]] is cached so as to prevent
    * repeated computation for the same input.
    *
    * @note
    *   All outputs of [[transform]] will be kept in memory until the returned
    *   structure is garbage-collected.
    */
  final inline def mapWithCache[Y <: Codomain : Context]
    (transform: X => Y)
    : Self[Y] = map(Cache.memoise(transform))

  /**
    * Performs a side effect for each contained value.
    *
    * @note
    *   Equivalent to `this.map{ value => effect(value); value }`.
    */
  final inline def forEach[Y >: X <: Codomain : Context]
    (effect: X => Any)
    : Self[Y] = map[Y]: value =>
    effect(value)
    value

  /**
    * Casts each element of this structure to type [[Y]].
    *
    * @note
    *   Equivalent to, but more efficient than `this.map(_.asInstanceOf[Y])`.
    */
  final inline def eachAsInstanceOf[Y <: Codomain : Context]: Self[Y] =
    asInstanceOf[Self[Y]]

  /** Split a structure of pairs into a pair of structures. */
  final inline def unzip[
    Y <: Codomain : Context,
    Z <: Codomain : Context,
  ](using split: X <:< (Y, Z)): (Self[Y], Self[Z]) =
    (map(split(_)(0)), map(split(_)(1)))
