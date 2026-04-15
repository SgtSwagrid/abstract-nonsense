package io.github.sgtswagrid.nonsense
package functor.covariant

/**
  * ## Context Functors
  *
  * A context functor is a restricted [[Functor]] that can only contain values
  * with certain properties.
  *
  * ### Constraints
  *
  *   1. A context bound [[Context]] required for the type [[X]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  *
  * @tparam Context
  *   The context bound on [[X]] (e.g. [[Numeric]]).
  *
  * @tparam X
  *   The type of value contained or produced (e.g. [[Int]]).
  *
  * @see
  *   If an upper bound is needed, use [[BoundedFunctor]] or [[PartialFunctor]]
  *   instead.
  */
trait ContextFunctor[+Self[+_], -Context[_], +X]
  extends PartialFunctor[Self, Any, Context, X]

object ContextFunctor:

  /** A [[ContextFunctor]] that never contains any value. */
  trait Empty[+Self : ValueOf, -Context[_]]
    extends ContextFunctor[[_] =>> Self, Context, Nothing]:

    override def map[Y : Context](transform: Nothing => Y): Self = valueOf[Self]
