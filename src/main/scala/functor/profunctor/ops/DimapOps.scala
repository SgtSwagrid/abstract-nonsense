package io.github.sgtswagrid.nonsense
package functor.profunctor.ops

import io.github.sgtswagrid.nonsense.caching.Cache
import io.github.sgtswagrid.nonsense.functor.profunctor.Profunctor

/**
  * The [[dimap]] operator for
  * [[functor.profunctor.BoundedProfunctor BoundedProfunctor]], and its
  * derivatives.
  */
trait DimapOps[
  +Self[-_, +_],
  +Domain,
  -Codomain,
  -I >: Domain,
  +O <: Codomain,
]:

  /**
    * ## `dimap` (from [[Profunctor]])
    *
    * Combines both [[map]] (from [[functor.covariant.Functor Functor]]) and
    * [[contramap]] (from [[functor.contravariant.Contravariant Contravariant]])
    * into a single operator.
    *
    * Used to simultaneously map both the input to and output from this
    * pipeline.
    *
    * @param pre
    *   The input transformation (to [[I]]).
    *
    * @param post
    *   The output transformation (from [[O]]).
    *
    * @tparam i
    *   The new input type to replace [[I]].
    *
    * @tparam o
    *   The new output type to replace [[O]].
    *
    * @return
    *   An extended version of this pipeline, leaving this original unchanged.
    */
  def dimap[i >: Domain, o <: Codomain](pre: i => I)(post: O => o): Self[i, o]

  /**
    * A version of [[dimap]] where the inputs to [[pre]] and [[post]] are
    * provided implicitly as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  final inline def dimapWithContext[i >: Domain, o <: Codomain]
    (pre: i ?=> I)
    (post: O ?=> o)
    : Self[i, o] =
    dimap[i, o](value => pre(using value))(value => post(using value))

  /**
    * A version of [[dimap]] where the transformations are cached to prevent
    * repeated computation for the same input.
    *
    * @note
    *   All outputs will be kept in memory until the returned structure is
    *   garbage-collected.
    */
  final inline def dimapWithCache[i >: Domain, o <: Codomain]
    (pre: i => I)
    (post: O => o)
    : Self[i, o] = dimap(Cache.memoise(pre))(Cache.memoise(post))
