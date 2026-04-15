package io.github.sgtswagrid.nonsense
package functor.bifunctor

import io.github.sgtswagrid.nonsense.functor.bifunctor.views.BifunctorRightView

/**
  * A restricted [[Bifunctor]] that can only contain particular values on the
  * left, but any value on the right.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam LeftCodomain
  *   The upper bound on [[Left]] following any [[map]]-like operation.
  *
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure (e.g.
  *   [[Int]]).
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure (e.g.
  *   [[Int]]).
  */
trait LeftBoundedBifunctor[
  +Self[+_, +_],
  -LeftCodomain,
  +Left <: LeftCodomain,
  +Right,
] extends BoundedBifunctor[Self, LeftCodomain, Any, Left, Right]:

  override final inline def right: BifunctorRightView[Self, Left, Right] =
    BifunctorRightView(this)

object LeftBoundedBifunctor:

  /**
    * A [[LeftBoundedBifunctor]] that contains no values.
    *
    * @tparam Self
    *   The singleton produced by all [[LeftBoundedBifunctor.bimap]]-like
    *   operations.
    *
    * @tparam LeftCodomain
    *   The upper bound on the left output of all
    *   [[LeftBoundedBifunctor.bimap]]-like operations.
    */
  trait Empty[+Self : ValueOf, -LeftCodomain]
    extends LeftBoundedBifunctor[
      [_, _] =>> Self,
      LeftCodomain,
      Nothing,
      Nothing,
    ]:

    override protected def bimapImpl[LeftPost <: LeftCodomain, RightPost]
      (transformLeft: Nothing => LeftPost)
      (transformRight: Nothing => RightPost)
      : Self = valueOf[Self]
