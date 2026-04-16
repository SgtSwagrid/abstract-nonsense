package io.github.sgtswagrid.nonsense
package functor.profunctor

/**
  * ## Input-Bounded Profunctors
  *
  * An input-bounded profunctor is a restricted [[Profunctor]] that can only
  * handle input values with certain properties, but any output value.
  *
  * ### Constraints
  *
  *   1. A lower bound [[Domain]] on the input type [[I]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Function1]]).
  *
  * @tparam Domain
  *   The lower bound on [[I]] (e.g. [[Nothing]]).
  *
  * @tparam I
  *   The type of value consumed (e.g. [[Int]]).
  *
  * @tparam O
  *   The type of value produced (e.g. [[Int]]).
  */
trait InputBoundedProfunctor[+Self[-_, +_], +Domain, -I >: Domain, +O]
  extends BoundedProfunctor[Self, Domain, Any, I, O]

object InputBoundedProfunctor:

  /** An [[InputBoundedProfunctor]] that never handles any value. */
  trait Empty[+Self : ValueOf]
    extends InputBoundedProfunctor[[_, _] =>> Self, Nothing, Nothing, Nothing]:

    override def dimap[i, o](pre: i => Nothing)(post: Nothing => o): Self =
      valueOf[Self]
