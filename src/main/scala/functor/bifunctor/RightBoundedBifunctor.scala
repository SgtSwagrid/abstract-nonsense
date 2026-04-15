package io.github.sgtswagrid.nonsense
package functor.bifunctor

import io.github.sgtswagrid.nonsense.functor.bifunctor.views.BifunctorLeftView

/**
  * A restricted [[Bifunctor]] that can only contain particular values on the
  * right, but any value on the left.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam RightCodomain
  *   The upper bound on [[Right]] following any [[map]]-like operation.
  *
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure (e.g.
  *   [[Int]]).
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure (e.g.
  *   [[Int]]).
  */
trait RightBoundedBifunctor[
  +Self[+_, +_],
  -RightCodomain,
  +Left,
  +Right <: RightCodomain,
] extends BoundedBifunctor[Self, Any, RightCodomain, Left, Right]:

  override final inline def left: BifunctorLeftView[Self, Left, Right] =
    BifunctorLeftView(this)

object RightBoundedBifunctor:

  /**
    * A [[RightBoundedBifunctor]] that contains no values.
    *
    * @tparam Self
    *   The singleton produced by all [[RightBoundedBifunctor.bimap]]-like
    *   operations.
    *
    * @tparam RightCodomain
    *   The upper bound on the right output of all
    *   [[RightBoundedBifunctor.bimap]]-like operations.
    */
  trait Empty[+Self : ValueOf, -RightCodomain]
    extends RightBoundedBifunctor[
      [_, _] =>> Self,
      RightCodomain,
      Nothing,
      Nothing,
    ]:

    override protected def bimapImpl[LeftPost, RightPost <: RightCodomain]
      (transformLeft: Nothing => LeftPost)
      (transformRight: Nothing => RightPost)
      : Self = valueOf[Self]
