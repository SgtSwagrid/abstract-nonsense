package io.github.sgtswagrid.nonsense
package functor.bifunctor

import io.github.sgtswagrid.nonsense.functor.bifunctor.views.{
  BifunctorLeftView, BifunctorRightView,
}

/**
  * A restricted [[Bifunctor]] that can only contain particular values.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam LeftCodomain
  *   The upper bound on [[Left]] following any [[BifunctorLeftView.map]]-like
  *   operation.
  *
  * @tparam RightCodomain
  *   The upper bound on [[Right]] following any [[BifunctorRightView.map]]-like
  *   operation.
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
  def bimap[PostLeft, PostRight]
    (transformLeft: Left => PostLeft)
    (transformRight: Right => PostRight)
    : Self[PostLeft, PostRight]

  /**
    * Provides a view of this structure that only maps over the left-hand side.
    *
    * @note
    *   Following any [[BoundedFunctor.map]]-like operation, the structure will
    *   be returned to its original form. The projection will thereafter be
    *   forgotten.
    */
  final inline def left
    : BifunctorLeftView[
      Self,
      LeftCodomain,
      RightCodomain,
      Left,
      Right,
    ] = BifunctorLeftView(this)

  /**
    * Provides a view of this structure that only maps over the right-hand side.
    *
    * @note
    *   Following any [[BoundedFunctor.map]]-like operation, the structure will
    *   be returned to its original form. The projection will thereafter be
    *   forgotten.
    */
  final inline def right
    : BifunctorRightView[
      Self,
      LeftCodomain,
      RightCodomain,
      Left,
      Right,
    ] = BifunctorRightView(this)
