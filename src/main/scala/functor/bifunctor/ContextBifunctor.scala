package io.github.sgtswagrid.nonsense
package functor.bifunctor

/**
  * ## Context Bifunctors
  *
  * A context bifunctor is a restricted [[Bifunctor]] that can only contain
  * values with certain properties.
  *
  * ### Constraints
  *
  *   1. A joint context bound [[Context]] on the types [[L]] and [[R]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam Context
  *   The joint context bound on [[L]] and [[R]].
  *
  * @tparam L
  *   The type of value contained on the left-hand side (e.g. [[Int]]).
  *
  * @tparam R
  *   The type of value contained on the right-hand side (e.g. [[Int]]).
  */
trait ContextBifunctor[+Self[+_, +_], -Context[_], +L, +R]
  extends PartialBifunctor[Self, Any, Any, Context, L, R]

object ContextBifunctor:

  /** A [[ContextBifunctor]] that never contains any value. */
  trait Empty[+Self : ValueOf, -Context[_]]
    extends ContextBifunctor[[_, _] =>> Self, Context, Nothing, Nothing]:

    override final def bimap[l, r]
      (using Context[l | r])
      (transformLeft: Nothing => l)
      (transformRight: Nothing => r)
      : Self = valueOf[Self]
