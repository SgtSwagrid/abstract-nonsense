package io.github.sgtswagrid.nonsense
package functor.bifunctor.ops

import io.github.sgtswagrid.nonsense.caching.Cache
import io.github.sgtswagrid.nonsense.functor.bifunctor.Bifunctor

/**
  * The [[bimap]] operator for
  * [[functor.bifunctor.PartialBifunctor PartialBifunctor]], and its
  * derivatives.
  */
trait BimapOps[
  +Self[+_, +_],
  -LeftCodomain,
  -RightCodomain,
  -Context[_, _],
  +L <: LeftCodomain,
  +R <: RightCodomain,
]:

  /**
    * ## `bimap` (from [[Bifunctor]])
    *
    * Combines two separate [[Functor.map]] calls into a single operator.
    *
    * Used to simultaneously transform both the left-hand and right-hand parts
    * of this structure.
    *
    * @param left
    *   An arbitrary mapping applied to each value on the left.
    *
    * @param right
    *   An arbitrary mapping applied to each value on the right.
    *
    * @return
    *   A projected version of this structure, leaving this original unchanged.
    *
    * @see
    *   [[functor.bifunctor.PartialBifunctor.left left]] or
    *   [[functor.bifunctor.PartialBifunctor.right right]] for projections that
    *   operate on only one side.
    */
  def bimap[l <: LeftCodomain, r <: RightCodomain]
    (using Context[l, r])
    (left: L => l)
    (right: R => r)
    : Self[l, r]

  /**
    * A version of [[bimap]] where the inputs to [[left]] and [[right]] are
    * provided implicitly as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  final inline def bimapWithContext[l <: LeftCodomain, r <: RightCodomain]
    (using Context[l, r])
    (left: L ?=> l)
    (right: R ?=> r)
    : Self[l, r] =
    bimap(value => left(using value))(value => right(using value))

  /**
    * A version of [[bimap]] where the transformations are cached to prevent
    * repeated computation for the same input.
    *
    * @note
    *   All outputs will be kept in memory until the returned structure is
    *   garbage-collected.
    */
  final inline def bimapWithCache[l <: LeftCodomain, r <: RightCodomain]
    (using Context[l, r])
    (left: L => l)
    (right: R => r)
    : Self[l, r] = bimap(Cache.memoise(left))(Cache.memoise(right))
