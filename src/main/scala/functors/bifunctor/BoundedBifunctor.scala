package io.github.sgtswagrid.nonsense
package functors.bifunctor

import io.github.sgtswagrid.nonsense.functors.bifunctor.views.{
  BoundedBifunctorLeftView, BoundedBifunctorRightView,
}

/**
  * A doubly-restricted [[Bifunctor]] that can only contain particular values on
  * both sides.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam LeftCodomain
  *   The upper bound on [[Left]] following any
  *   [[BoundedBifunctorLeftView.map]]-like operation.
  *
  * @tparam RightCodomain
  *   The upper bound on [[Right]] following any
  *   [[BoundedBifunctorRightView.map]]-like operation.
  *
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure (e.g.
  *   [[Int]]).
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure (e.g.
  *   [[Int]]).
  */
trait BoundedBifunctor[
  +Self[+_, +_],
  -LeftCodomain,
  -RightCodomain,
  +Left <: LeftCodomain,
  +Right <: RightCodomain,
]:

  /**
    * Simultaneously transform both the left-hand and right-hand parts of this
    * structure.
    */
  def bimap[LeftPost, RightPost]
    (transformLeft: Left => LeftPost)
    (transformRight: Right => RightPost)
    : Self[LeftPost, RightPost]

  /**
    * Provides a view of this structure that only maps over the left-hand side.
    *
    * @note
    *   Following any [[BoundedFunctor.map]]-like operation, the structure will
    *   be returned to its original form. The projection will thereafter be
    *   forgotten.
    */
  def left: BoundedBifunctorLeftView[Self, LeftCodomain, Left, Right] =
    BoundedBifunctorLeftView(this)

  /**
    * Provides a view of this structure that only maps over the right-hand side.
    *
    * @note
    *   Following any [[BoundedFunctor.map]]-like operation, the structure will
    *   be returned to its original form. The projection will thereafter be
    *   forgotten.
    */
  def right: BoundedBifunctorRightView[Self, RightCodomain, Left, Right] =
    BoundedBifunctorRightView(this)
