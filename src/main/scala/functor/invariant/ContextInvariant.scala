package io.github.sgtswagrid.nonsense
package functor.invariant

/**
  * ## Context Invariant Functors
  *
  * A context invariant functor is a restricted [[Invariant]] that can only
  * contain values with certain properties.
  *
  * ### Constraints
  *
  *   1. A context bound [[Context]] required for the type [[X]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is.
  *
  * @tparam Context
  *   The context bound on [[X]] (e.g. [[Numeric]]).
  *
  * @tparam X
  *   The type of value contained (e.g. [[Int]]).
  *
  * @see
  *   If a bound is needed, use [[BoundedInvariant]] or [[PartialInvariant]]
  *   instead.
  */
trait ContextInvariant[+Self[_], -Context[_], X]
  extends PartialInvariant[Self, Nothing, Any, Context, X]

object ContextInvariant:

  /** A [[ContextInvariant]] with a [[Numeric]] context bound. */
  type NumericInvariant[+Self[_], X] = ContextInvariant[Self, Numeric, X]

  /** A [[ContextInvariant]] with an [[Integral]] context bound. */
  type IntegralInvariant[+Self[_], X] = ContextInvariant[Self, Integral, X]

  /** A [[ContextInvariant]] with a [[Fractional]] context bound. */
  type FractionalInvariant[+Self[_], X] = ContextInvariant[Self, Fractional, X]

  /** A [[ContextInvariant]] that never contains any value. */
  trait Empty[+Self : ValueOf, -Context[_]]
    extends ContextInvariant[[_] =>> Self, Context, Nothing]:

    override def xmap[Y : Context]
      (forward: Nothing => Y)
      (backward: Y => Nothing)
      : Self = valueOf[Self]
