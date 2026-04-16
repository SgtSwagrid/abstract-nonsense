package io.github.sgtswagrid.nonsense
package functor.bifunctor

import io.github.sgtswagrid.nonsense.functor.bifunctor.ops.BimapOps
import io.github.sgtswagrid.nonsense.functor.bifunctor.views.projection.{
  PartialBifunctorLeftView, PartialBifunctorRightView,
}
import io.github.sgtswagrid.nonsense.functor.bifunctor.views.swap.PartialSwappedBifunctorView

/**
  * ## Partial Bifunctors
  *
  * A context bifunctor is a doubly-restricted [[Bifunctor]] that can only
  * contain values with certain properties on both sides.
  *
  * ### Constraints
  *
  *   1. An upper bound [[LeftCodomain]] on the type [[L]].
  *   2. An upper bound [[RightCodomain]] on the type [[R]].
  *   3. A joint context bound [[Context]] on the types [[L]] and [[R]].
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
trait PartialBifunctor[
  +Self[+_, +_],
  -LeftCodomain,
  -RightCodomain,
  -Context[_, _],
  +L <: LeftCodomain,
  +R <: RightCodomain,
] extends BimapOps[
    Self,
    LeftCodomain,
    RightCodomain,
    Context,
    L,
    R,
  ]:

  /**
    * Provides a view of this structure with the left and right sides swapped.
    *
    * @note
    *   Following any [[bimap]]-like operation, the structure will be returned
    *   to its original form. The projection will thereafter be forgotten.
    */
  def swap
    : PartialSwappedBifunctorView[
      Self,
      RightCodomain,
      LeftCodomain,
      [l, r] =>> Context[r, l],
      R,
      L,
    ] = PartialSwappedBifunctorView(this)

  /**
    * Provides a view of this structure that only maps over the left-hand side.
    *
    * @note
    *   Following any [[bimap]]-like operation, the structure will be returned
    *   to its original form. The projection will thereafter be forgotten.
    */
  def left: PartialBifunctorLeftView[Self, LeftCodomain, Context, L, R] =
    PartialBifunctorLeftView(this)

  /**
    * Provides a view of this structure that only maps over the right-hand side.
    *
    * @note
    *   Following any [[bimap]]-like operation, the structure will be returned
    *   to its original form. The projection will thereafter be forgotten.
    */
  def right: PartialBifunctorRightView[Self, RightCodomain, Context, L, R] =
    PartialBifunctorRightView(this)

object PartialBifunctor:

  /**
    * A [[PartialBifunctor]] with symmetric constraints. That is, both sides
    * have the same requirements.
    */
  type Symmetric[
    +Self[+_, +_],
    -Codomain,
    -Context[_, _],
    +Left <: Codomain,
    +Right <: Codomain,
  ] = PartialBifunctor[
    Self,
    Codomain,
    Codomain,
    Context,
    Left,
    Right,
  ]

  /**
    * A context where both sides must satisfy a shared constraint via their
    * union type. That is, `Context[L, R]` holds iff `C[L | R]` holds.
    */
  type Union[C[_]] = [l, r] =>> C[l | r]

  /**
    * A context where each side must independently satisfy its own constraint.
    * That is, `Context[L, R]` holds iff both `C1[L]` and `C2[R]` hold.
    */
  type Independent[C1[_], C2[_]] = [l, r] =>> (C1[l], C2[r])

  /** A [[PartialBifunctor]] that never contains any value. */
  trait Empty[
    +Self : ValueOf,
    -LeftCodomain,
    -RightCodomain,
    -Context[_, _],
  ] extends PartialBifunctor[
      [_, _] =>> Self,
      LeftCodomain,
      RightCodomain,
      Context,
      Nothing,
      Nothing,
    ]:

    override final def bimap[l <: LeftCodomain, r <: RightCodomain]
      (using Context[l, r])
      (left: Nothing => l)
      (right: Nothing => r)
      : Self = valueOf[Self]
