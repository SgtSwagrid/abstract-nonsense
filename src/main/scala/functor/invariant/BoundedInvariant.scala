package io.github.sgtswagrid.nonsense
package functor.invariant

import io.github.sgtswagrid.nonsense.util.NoContext

/**
  * ## Bounded Invariant Functors
  *
  * A bounded invariant functor is a restricted [[Invariant]] that can only
  * contain values with certain properties.
  *
  * ### Constraints
  *
  *   1. A lower bound [[Domain]] on the type [[X]].
  *   2. An upper bound [[Codomain]] on the type [[X]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is.
  *
  * @tparam Domain
  *   The lower bound on [[X]] (e.g. [[Nothing]]).
  *
  * @tparam Codomain
  *   The upper bound on [[X]] (e.g. [[Any]]).
  *
  * @tparam X
  *   The type of value contained (e.g. [[Int]]).
  *
  * @see
  *   If a context bound is needed, use [[ContextInvariant]] or
  *   [[PartialInvariant]] instead.
  */
trait BoundedInvariant[
  +Self[_],
  +Domain,
  -Codomain,
  X >: Domain <: Codomain,
] extends PartialInvariant[Self, Domain, Codomain, NoContext, X]

object BoundedInvariant:

  /** A [[BoundedInvariant]] that never contains any value. */
  trait Empty[+Self : ValueOf, -Codomain]
    extends BoundedInvariant[[_] =>> Self, Nothing, Codomain, Nothing]:

    override def xmap[Y <: Codomain : NoContext]
      (forward: Nothing => Y)
      (backward: Y => Nothing)
      : Self = valueOf[Self]
