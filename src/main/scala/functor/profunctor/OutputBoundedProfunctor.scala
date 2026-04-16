package io.github.sgtswagrid.nonsense
package functor.profunctor

/**
  * ## Output-Bounded Profunctors
  *
  * An output-bounded profunctor is a restricted [[Profunctor]] that can only
  * handle output values with certain properties, but any input value.
  *
  * ### Constraints
  *
  *   1. An upper bound [[Codomain]] on the output type [[O]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Function1]]).
  *
  * @tparam Codomain
  *   The upper bound on [[O]] (e.g. [[Any]]).
  *
  * @tparam I
  *   The type of value consumed (e.g. [[Int]]).
  *
  * @tparam O
  *   The type of value produced (e.g. [[Int]]).
  */
trait OutputBoundedProfunctor[
  +Self[-_, +_],
  -Codomain,
  -I,
  +O <: Codomain,
] extends BoundedProfunctor[Self, Nothing, Codomain, I, O]

object OutputBoundedProfunctor:

  /** An [[OutputBoundedProfunctor]] that never handles any value. */
  trait Empty[+Self : ValueOf, -Codomain]
    extends OutputBoundedProfunctor[
      [_, _] =>> Self,
      Codomain,
      Nothing,
      Nothing,
    ]:

    override def dimap[i, o <: Codomain]
      (pre: i => Nothing)
      (post: Nothing => o)
      : Self = valueOf[Self]
