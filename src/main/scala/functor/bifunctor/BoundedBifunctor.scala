package io.github.sgtswagrid.nonsense
package functor.bifunctor

import io.github.sgtswagrid.nonsense.functor.bifunctor.views.{
  BoundedBifunctorLeftView, BoundedBifunctorRightView,
}

/**
  * A doubly-restricted [[Bifunctor]] that can only contain particular values on
  * both sides, constrained by type bounds.
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
] extends BoundedContextBifunctor[
    Self,
    LeftCodomain,
    RightCodomain,
    [_] =>> DummyImplicit,
    Left,
    Right,
  ]:

  override final inline def bimap[
    LeftPost <: LeftCodomain,
    RightPost <: RightCodomain,
  ]
    (using DummyImplicit)
    (transformLeft: Left => LeftPost)
    (transformRight: Right => RightPost)
    : Self[LeftPost, RightPost] = bimapImpl(transformLeft)(transformRight)

  /** The internal implementation of [[bimap]]. */
  protected def bimapImpl[
    LeftPost <: LeftCodomain,
    RightPost <: RightCodomain,
  ](transformLeft: Left => LeftPost)(transformRight: Right => RightPost)
    : Self[LeftPost, RightPost]

  /**
    * Provides a view of this structure that only maps over the left-hand side.
    *
    * @note
    *   Following any [[BoundedFunctor.map]]-like operation, the structure will
    *   be returned to its original form. The projection will thereafter be
    *   forgotten.
    */
  override def left: BoundedBifunctorLeftView[Self, LeftCodomain, Left, Right] =
    BoundedBifunctorLeftView(this)

  /**
    * Provides a view of this structure that only maps over the right-hand side.
    *
    * @note
    *   Following any [[BoundedFunctor.map]]-like operation, the structure will
    *   be returned to its original form. The projection will thereafter be
    *   forgotten.
    */
  override def right: BoundedBifunctorRightView[Self, RightCodomain, Left, Right] =
    BoundedBifunctorRightView(this)

object BoundedBifunctor:

  /**
    * A [[BoundedBifunctor]] with symmetric constraints. That is, both sides
    * have the same requirements.
    */
  type Symmetric[
    +Self[+_, +_],
    -Codomain,
    +Left <: Codomain,
    +Right <: Codomain,
  ] = BoundedBifunctor[Self, Codomain, Codomain, Left, Right]

  /**
    * A [[BoundedBifunctor]] that contains no values.
    *
    * @tparam Self
    *   The singleton produced by all [[BoundedBifunctor.bimap]]-like
    *   operations.
    *
    * @tparam LeftCodomain
    *   The upper bound on the left output of all
    *   [[BoundedBifunctor.bimap]]-like operations.
    *
    * @tparam RightCodomain
    *   The upper bound on the right output of all
    *   [[BoundedBifunctor.bimap]]-like operations.
    */
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

    override protected def bimapImpl[
      LeftPost <: LeftCodomain,
      RightPost <: RightCodomain,
    ]
      (transformLeft: Nothing => LeftPost)
      (transformRight: Nothing => RightPost)
      : Self = valueOf[Self]
