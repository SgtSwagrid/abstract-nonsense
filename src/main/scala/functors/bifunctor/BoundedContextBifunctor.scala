package io.github.sgtswagrid.nonsense
package functors.bifunctor

import io.github.sgtswagrid.nonsense.functors.bifunctor.views.{
  BoundedContextBifunctorLeftView, BoundedContextBifunctorRightView,
}
import scala.annotation.unchecked.uncheckedVariance

/**
  * A doubly-restricted [[Bifunctor]] that can only contain particular values on
  * both sides, constrained by both type and context bounds.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam LeftCodomain
  *   The upper bound on [[Left]] following any [[bimap]]-like operation.
  *
  * @tparam RightCodomain
  *   The upper bound on [[Right]] following any [[bimap]]-like operation.
  *
  * @tparam LeftContext
  *   The context bound on [[Left]] that must be present following any
  *   [[bimap]]-like operation.
  *
  * @tparam RightContext
  *   The context bound on [[Right]] that must be present following any
  *   [[bimap]]-like operation.
  *
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure.
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure.
  */
trait BoundedContextBifunctor[
  +Self[+_, +_],
  -LeftCodomain,
  -RightCodomain,
  -LeftContext[_],
  -RightContext[_],
  +Left <: LeftCodomain,
  +Right <: RightCodomain,
]:

  /**
    * Simultaneously transform both the left-hand and right-hand parts of this
    * structure.
    */
  def bimap[
    LeftPost <: LeftCodomain : LeftContext,
    RightPost <: RightCodomain : RightContext,
  ](transformLeft: Left => LeftPost)(transformRight: Right => RightPost)
    : Self[LeftPost, RightPost]

  /**
    * Provides a view of this structure that only maps over the left-hand side.
    *
    * @note
    *   Following any [[BoundedContextFunctor.map]]-like operation, the
    *   structure will be returned to its original form. The projection will
    *   thereafter be forgotten.
    */
  def left(using RightContext[Right @uncheckedVariance])
    : BoundedContextBifunctorLeftView[
      Self,
      LeftCodomain,
      LeftContext,
      RightContext,
      Left,
      Right,
    ] = BoundedContextBifunctorLeftView(this)

  /**
    * Provides a view of this structure that only maps over the right-hand side.
    *
    * @note
    *   Following any [[BoundedContextFunctor.map]]-like operation, the
    *   structure will be returned to its original form. The projection will
    *   thereafter be forgotten.
    */
  def right(using LeftContext[Left @uncheckedVariance])
    : BoundedContextBifunctorRightView[
      Self,
      RightCodomain,
      LeftContext,
      RightContext,
      Left,
      Right,
    ] = BoundedContextBifunctorRightView(this)

object BoundedContextBifunctor:

  /**
    * A [[BoundedContextBifunctor]] with symmetric constraints. That is, both
    * sides have the same requirements.
    */
  type Symmetric[
    +Self[+_, +_],
    -Codomain,
    -Context[_],
    +Left <: Codomain,
    +Right <: Codomain,
  ] = BoundedContextBifunctor[
    Self,
    Codomain,
    Codomain,
    Context,
    Context,
    Left,
    Right,
  ]

  /**
    * A [[BoundedContextBifunctor]] that contains no values.
    *
    * @tparam Self
    *   The singleton produced by all [[BoundedContextBifunctor.bimap]]-like
    *   operations.
    *
    * @tparam LeftCodomain
    *   The upper bound on the left output of all
    *   [[BoundedContextBifunctor.bimap]]-like operations.
    *
    * @tparam RightCodomain
    *   The upper bound on the right output of all
    *   [[BoundedContextBifunctor.bimap]]-like operations.
    *
    * @tparam LeftContext
    *   The context bound required on the left output of all
    *   [[BoundedContextBifunctor.bimap]]-like operations.
    *
    * @tparam RightContext
    *   The context bound required on the right output of all
    *   [[BoundedContextBifunctor.bimap]]-like operations.
    */
  trait Empty[
    +Self : ValueOf,
    -LeftCodomain,
    -RightCodomain,
    -LeftContext[_],
    -RightContext[_],
  ] extends BoundedContextBifunctor[
      [_, _] =>> Self,
      LeftCodomain,
      RightCodomain,
      LeftContext,
      RightContext,
      Nothing,
      Nothing,
    ]:
    override def bimap[
      LeftPost <: LeftCodomain : LeftContext,
      RightPost <: RightCodomain : RightContext,
    ](transformLeft: Nothing => LeftPost)
      (transformRight: Nothing => RightPost)
      : Self = valueOf[Self]
