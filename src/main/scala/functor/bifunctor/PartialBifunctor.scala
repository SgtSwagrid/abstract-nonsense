package io.github.sgtswagrid.nonsense
package functor.bifunctor

import io.github.sgtswagrid.nonsense.functor.bifunctor.views.{
  PartialBifunctorLeftView, PartialBifunctorRightView,
}
import io.github.sgtswagrid.nonsense.functor.covariant.Functor

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
  -Context[_],
  +L <: LeftCodomain,
  +R <: RightCodomain,
]:

  /**
    * ## `bimap` (from [[Bifunctor]])
    *
    * [[bimap]] combines two separate [[Functor.map]] implementations into a
    * single operator.
    *
    * Used to simultaneously transform both the left-hand and right-hand parts
    * of this structure.
    *
    * @param left
    *   An arbitrary mapping applied to each value on the left.
    *
    * @param right
    *   An arbitrary mapping applied to each value on the right.
    *
    * @return
    *   A projected version of this structure, leaving this original unchanged.
    *
    * @see
    *   [[left]] or [[right]] for projections that operate on only one side.
    */
  def bimap[l <: LeftCodomain, r <: RightCodomain]
    (using Context[l | r])
    (left: L => l)
    (right: R => r)
    : Self[l, r]

  /**
    * Provides a view of this structure that only maps over the left-hand side.
    *
    * @note
    *   Following any [[BoundedContextFunctor.map]]-like operation, the
    *   structure will be returned to its original form. The projection will
    *   thereafter be forgotten.
    */
  def left: PartialBifunctorLeftView[Self, LeftCodomain, Context, L, R] =
    PartialBifunctorLeftView(this)

  /**
    * Provides a view of this structure that only maps over the right-hand side.
    *
    * @note
    *   Following any [[BoundedContextFunctor.map]]-like operation, the
    *   structure will be returned to its original form. The projection will
    *   thereafter be forgotten.
    */
  def right
    : PartialBifunctorRightView[Self, RightCodomain, Context, L, R] =
    PartialBifunctorRightView(this)

object PartialBifunctor:

  /**
    * A [[PartialBifunctor]] with symmetric constraints. That is, both sides
    * have the same requirements.
    */
  type Symmetric[
    +Self[+_, +_],
    -Codomain,
    -Context[_],
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

  /** A [[PartialBifunctor]] that never contains any value. */
  trait Empty[
    +Self : ValueOf,
    -LeftCodomain,
    -RightCodomain,
    -Context[_],
  ] extends PartialBifunctor[
      [_, _] =>> Self,
      LeftCodomain,
      RightCodomain,
      Context,
      Nothing,
      Nothing,
    ]:

    override final def bimap[l <: LeftCodomain, r <: RightCodomain]
      (using Context[l | r])
      (left: Nothing => l)
      (right: Nothing => r)
      : Self = valueOf[Self]
