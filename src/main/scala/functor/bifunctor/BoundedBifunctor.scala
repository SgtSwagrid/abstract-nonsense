package io.github.sgtswagrid.nonsense
package functor.bifunctor

import io.github.sgtswagrid.nonsense.util.NoContext
import io.github.sgtswagrid.nonsense.functor.bifunctor.views.{
  BoundedBifunctorLeftView, BoundedBifunctorRightView,
}

/**
  * ## Bounded Bifunctors
  *
  * A bounded bifunctor is a doubly-restricted [[Bifunctor]] that can only
  * contain values with certain properties on both sides.
  *
  * ### Constraints
  *
  *   1. An upper bound [[LeftCodomain]] on the type [[L]].
  *   2. An upper bound [[RightCodomain]] on the type [[R]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam LeftCodomain
  *   The upper bound on [[L]] (e.g. [[Any]]).
  *
  * @tparam RightCodomain
  *   The upper bound on [[R]] (e.g. [[Any]]).
  *
  * @tparam L
  *   The type of value contained on the left-hand side (e.g. [[Int]]).
  *
  * @tparam R
  *   The type of value contained on the right-hand side (e.g. [[Int]]).
  */
trait BoundedBifunctor[
  +Self[+_, +_],
  -LeftCodomain,
  -RightCodomain,
  +L <: LeftCodomain,
  +R <: RightCodomain,
] extends PartialBifunctor[
    Self,
    LeftCodomain,
    RightCodomain,
    NoContext,
    L,
    R,
  ]:

  /**
    * Provides a view of this structure that only maps over the left-hand side.
    *
    * @note
    *   Following any [[BoundedFunctor.map]]-like operation, the structure will
    *   be returned to its original form. The projection will thereafter be
    *   forgotten.
    */
  override def left: BoundedBifunctorLeftView[Self, LeftCodomain, L, R] =
    BoundedBifunctorLeftView(this)

  /**
    * Provides a view of this structure that only maps over the right-hand side.
    *
    * @note
    *   Following any [[BoundedFunctor.map]]-like operation, the structure will
    *   be returned to its original form. The projection will thereafter be
    *   forgotten.
    */
  override def right: BoundedBifunctorRightView[Self, RightCodomain, L, R] =
    BoundedBifunctorRightView(this)

object BoundedBifunctor:

  /** A [[BoundedBifunctor]] with symmetric upper bounds. */
  type Symmetric[
    +Self[+_, +_],
    -Codomain,
    +L <: Codomain,
    +R <: Codomain,
  ] = BoundedBifunctor[Self, Codomain, Codomain, L, R]

  /** A [[BoundedBifunctor]] that never contains any value. */
  trait Empty[
    +Self : ValueOf,
    -LeftCodomain,
    -RightCodomain,
  ] extends BoundedBifunctor[
      [_, _] =>> Self,
      LeftCodomain,
      RightCodomain,
      Nothing,
      Nothing,
    ]:

    override def bimap[l <: LeftCodomain, r <: RightCodomain]
      (using DummyImplicit)
      (transformLeft: Nothing => l)
      (transformRight: Nothing => r)
      : Self = valueOf[Self]
