package io.github.sgtswagrid.nonsense
package functor.invariant

import io.github.sgtswagrid.nonsense.functor.invariant.ops.XmapToXOps

/**
  * ## Partial Invariant Functors
  *
  * A partial invariant functor is a restricted [[Invariant]] that can only
  * contain values with certain properties.
  *
  * ### Constraints
  *
  *   1. A lower bound [[Domain]] on the type [[X]].
  *   2. An upper bound [[Codomain]] on the type [[X]].
  *   3. A context bound [[Context]] required for the type [[X]].
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
  * @tparam Context
  *   The context bound on [[X]] (e.g. [[Numeric]]).
  *
  * @tparam X
  *   The type of value contained (e.g. [[Int]]).
  *
  * @see
  *   If only one or two of the above constraints are needed, instead use
  *   [[BoundedInvariant]], [[ContextInvariant]], or their combination.
  */
trait PartialInvariant[
  +Self[_],
  +Domain,
  -Codomain,
  -Context[_],
  X >: Domain <: Codomain,
] extends XmapToXOps[Self, Domain, Codomain, Context, X]

object PartialInvariant:

  /** A [[PartialInvariant]] that never contains any value. */
  trait Empty[+Self : ValueOf, -Codomain, -Context[_]]
    extends PartialInvariant[
      [_] =>> Self,
      Nothing,
      Codomain,
      Context,
      Nothing,
    ]:

    override def xmap[Y <: Codomain : Context]
      (forward: Nothing => Y)
      (backward: Y => Nothing)
      : Self = valueOf[Self]
