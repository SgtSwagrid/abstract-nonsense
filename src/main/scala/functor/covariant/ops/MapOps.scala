package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import io.github.sgtswagrid.nonsense.caching.Cache

/** The [[map]] operator for [[BoundedFunctor]], and its derivatives. */
trait MapOps[+Self[+_], -Codomain, +Output <: Codomain]:

  /**
    * Produce an equivalent version of this structure whereby the contents have
    * been modified arbitrarily by [[transform]].
    *
    * @param transform
    *   The mapping to apply to each individual element in the structure.
    *
    * @note
    *   This original structure is left unmodified by the operation.
    *
    * @note
    *   The exact semantics depend on the kind of structure being transformed.
    *
    * @example
    *   {{{
    * val a = List(1, 2, 3, 4)
    * val b = a.map(_ * 2)
    * // b == List(2, 4, 6, 8)
    *   }}}
    */
  def map[Post <: Codomain](transform: Output => Post): Self[Post]

  /**
    * A version of [[map]] where the input to [[transform]] is provided
    * implicitly as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  final inline def mapCtx[Post <: Codomain]
    (transform: Output ?=> Post)
    : Self[Post] = map(value => transform(using value))

  /**
    * A version of [[map]] where [[transform]] is cached so as to prevent
    * repeated computation for the same input.
    *
    * @note
    *   All outputs of [[transform]] will be kept in memory until the returned
    *   structure is garbage-collected.
    */
  final inline def mapWithCache[Post <: Codomain]
    (transform: Output => Post)
    : Self[Post] = map(Cache.memoise(transform))

  /**
    * Performs a side effect for each contained value.
    *
    * @note
    *   Equivalent to `this.map{ value => effect(value); value }`.
    */
  final inline def forEach(effect: Output => Any): Self[Output] =
    mapWithEvidence: value =>
      effect(value)
      value

  /**
    * Casts each element of this structure to type [[Post]].
    *
    * @note
    *   Equivalent to, but more efficient than `this.map(_.asInstanceOf[Post])`.
    */
  final inline def eachAsInstanceOf[Post <: Codomain]: Self[Post] =
    asInstanceOf[Self[Post]]

  /** Split a structure of pairs into a pair of structures. */
  final inline def unzip[Left <: Codomain, Right <: Codomain]
    (using split: Output <:< (Left, Right))
    : (Self[Left], Self[Right]) = (map(split(_)(0)), map(split(_)(1)))

  protected inline def mapWithEvidence[Post]
    (using Post <:< Codomain)
    (transform: Output => Post)
    : Self[Post] = map(transform(_).asInstanceOf[Post & Codomain])

  protected inline def mapToWithEvidence[Post]
    (using Post <:< Codomain)
    (value: => Post)
    : Self[Post] = mapWithEvidence(_ => value)
