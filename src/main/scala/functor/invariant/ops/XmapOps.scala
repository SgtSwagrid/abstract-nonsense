package io.github.sgtswagrid.nonsense
package functor.invariant.ops

import io.github.sgtswagrid.nonsense.caching.Cache
import io.github.sgtswagrid.nonsense.functor.invariant.Invariant

/** The [[xmap]] operator for [[BoundedInvariant]], and its derivatives. */
trait XmapOps[
  +Self[_],
  +Domain,
  -Codomain,
  -Context[_],
  X >: Domain <: Codomain,
]:

  /**
    * ## `xmap` (from [[Invariant]])
    *
    * Transforms the value in this structure in both directions simultaneously.
    *
    * @param forward
    *   The transformation applied when reading a value out.
    *
    * @param backward
    *   The transformation applied when writing a value in.
    *
    * @tparam Y
    *   The new value type to replace [[X]].
    *
    * @return
    *   A projected version of this structure, leaving this original unchanged.
    */
  def xmap[Y >: Domain <: Codomain : Context](forward: X => Y)(backward: Y => X)
    : Self[Y]

  /**
    * A version of [[xmap]] where the input to [[forward]] is provided
    * implicitly as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  final inline def xmapWithContext[Y >: Domain <: Codomain : Context]
    (forward: X ?=> Y)
    (backward: Y => X)
    : Self[Y] = xmap(value => forward(using value))(backward)

  /**
    * A version of [[xmap]] where [[forward]] is cached to prevent repeated
    * computation for the same input.
    *
    * @note
    *   All outputs of [[forward]] will be kept in memory until the returned
    *   structure is garbage-collected.
    */
  final inline def xmapWithCache[Y >: Domain <: Codomain : Context]
    (forward: X => Y)
    (backward: Y => X)
    : Self[Y] = xmap(Cache.memoise(forward))(backward)
