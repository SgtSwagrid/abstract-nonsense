package io.github.sgtswagrid.nonsense
package functor.profunctor

import io.github.sgtswagrid.nonsense.functor.profunctor.ops.PartialDimapOps

/**
  * ## Partial Profunctors
  *
  * A partial profunctor is a doubly-restricted [[Profunctor]] that can only
  * handle values with certain properties on both sides.
  *
  * ### Constraints
  *
  *   1. A lower bound [[Domain]] on the input type [[I]].
  *   2. An upper bound [[Codomain]] on the output type [[O]].
  *   3. A joint context bound [[Context]] on the types [[I]] and [[O]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Function1]]).
  *
  * @tparam Domain
  *   The lower bound on [[I]] (e.g. [[Nothing]]).
  *
  * @tparam Codomain
  *   The upper bound on [[O]] (e.g. [[Any]]).
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
trait PartialProfunctor[
  +Self[-_, +_],
  +Domain,
  -Codomain,
  -Context[_, _],
  -I >: Domain,
  +O <: Codomain,
] extends PartialDimapOps[Self, Domain, Codomain, Context, I, O]

object PartialProfunctor:

  /**
    * A context where both sides must satisfy a shared constraint via their
    * union type. That is, `Context[I, O]` holds iff `C[I | O]` holds.
    */
  type Union[C[_]] = [i, o] =>> C[i | o]

  /**
    * A context where each side must independently satisfy its own constraint.
    * That is, `Context[I, O]` holds iff both `C1[I]` and `C2[O]` hold.
    */
  type Independent[C1[_], C2[_]] = [i, o] =>> (C1[i], C2[o])

  /** A [[PartialProfunctor]] with symmetric constraints. */
  type Symmetric[
    +Self[-_, +_],
    +Domain,
    -Codomain,
    -Context[_, _],
    -I >: Domain,
    +O <: Codomain,
  ] = PartialProfunctor[Self, Domain, Codomain, Context, I, O]

  /** A [[PartialProfunctor]] that never handles any value. */
  trait Empty[+Self : ValueOf, -Codomain, -Context[_, _]]
    extends PartialProfunctor[
      [_, _] =>> Self,
      Nothing,
      Codomain,
      Context,
      Nothing,
      Nothing,
    ]:

    override final def dimap[i, o <: Codomain]
      (using Context[i, o])
      (pre: i => Nothing)
      (post: Nothing => o)
      : Self = valueOf[Self]
