package io.github.sgtswagrid.nonsense
package functor.profunctor

/**
  * ## Context Profunctors
  *
  * A context profunctor is a restricted [[Profunctor]] that can only handle
  * values with certain properties.
  *
  * ### Constraints
  *
  *   1. A joint context bound [[Context]] on the types [[I]] and [[O]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Function1]]).
  *
  * @tparam Context
  *   The joint context bound on [[I]] and [[O]].
  *
  * @tparam I
  *   The type of value consumed (e.g. [[Int]]).
  *
  * @tparam O
  *   The type of value produced (e.g. [[Int]]).
  */
trait ContextProfunctor[+Self[-_, +_], -Context[_], -I, +O]
  extends PartialProfunctor[
    Self,
    Nothing,
    Any,
    [i, o] =>> Context[i | o],
    I,
    O,
  ]

object ContextProfunctor:

  /** A [[ContextProfunctor]] that never handles any value. */
  trait Empty[+Self : ValueOf, -Context[_]]
    extends ContextProfunctor[[_, _] =>> Self, Context, Nothing, Nothing]:

    override final def dimap[i, o]
      (using Context[i | o])
      (pre: i => Nothing)
      (post: Nothing => o)
      : Self = valueOf[Self]
