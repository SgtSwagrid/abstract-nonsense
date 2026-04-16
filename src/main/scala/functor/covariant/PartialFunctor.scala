package io.github.sgtswagrid.nonsense
package functor.covariant

import io.github.sgtswagrid.nonsense.functor.covariant.ops.MapToXOps

/**
  * ## Partial Functors
  *
  * A partial functor is a restricted [[Functor]] that can only contain values
  * with certain properties.
  *
  * ### Constraints
  *
  *   1. An upper bound [[Codomain]] on the type [[X]].
  *   2. A context bound [[Context]] required for the type [[X]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  *
  * @tparam Codomain
  *   The upper bound on [[X]] (e.g. [[Any]]).
  *
  * @tparam Context
  *   The context bound on [[X]] (e.g. [[Numeric]]).
  *
  * @tparam X
  *   The type of value contained or produced (e.g. [[Int]]).
  *
  * @see
  *   If only one of the above constraints is needed, instead use
  *   [[BoundedFunctor]] or [[ContextFunctor]] respectively.
  */
trait PartialFunctor[
  +Self[+_ <: Codomain],
  -Codomain,
  -Context[_ <: Codomain],
  +X <: Codomain,
] extends MapToXOps[Self, Codomain, Context, X]

object PartialFunctor:

  /** A [[PartialFunctor]] that never contains any value. */
  trait Empty[+Self : ValueOf, -Codomain, -Context[_]]
    extends PartialFunctor[[_] =>> Self, Codomain, Context, Nothing]:

    override def map[Y <: Codomain : Context](transform: Nothing => Y): Self =
      valueOf[Self]

  /** A context bound which indicates that no context is required. */
  type NoContext[_] = DummyImplicit
