package io.github.sgtswagrid.nonsense
package functors.bifunctor

import io.github.sgtswagrid.nonsense.functors.bifunctor.views.{
  ContextBifunctorLeftView, ContextBifunctorRightView,
}
import scala.annotation.unchecked.uncheckedVariance

/**
  * A restricted [[Bifunctor]] that can only contain particular values on both
  * sides, constrained by context bounds.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
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
trait ContextBifunctor[
  +Self[+_, +_],
  -LeftContext[_],
  -RightContext[_],
  +Left,
  +Right,
] extends BoundedContextBifunctor[
    Self,
    Any,
    Any,
    LeftContext,
    RightContext,
    Left,
    Right,
  ]:

  override def left
    (using RightContext[Right @uncheckedVariance])
    : ContextBifunctorLeftView[
      Self,
      LeftContext,
      RightContext,
      Left,
      Right,
    ] = ContextBifunctorLeftView(this)

  override def right
    (using LeftContext[Left @uncheckedVariance])
    : ContextBifunctorRightView[
      Self,
      LeftContext,
      RightContext,
      Left,
      Right,
    ] = ContextBifunctorRightView(this)

object ContextBifunctor:

  /**
    * A [[ContextBifunctor]] with symmetric constraints. That is, both sides
    * have the same requirements.
    */
  type Symmetric[+Self[+_, +_], -Context[_], +Left, +Right] =
    ContextBifunctor[Self, Context, Context, Left, Right]

  /**
    * A [[ContextBifunctor]] that contains no values.
    *
    * @tparam Self
    *   The singleton produced by all [[ContextBifunctor.bimap]]-like
    *   operations.
    *
    * @tparam LeftContext
    *   The context bound required on the left output of all
    *   [[ContextBifunctor.bimap]]-like operations.
    *
    * @tparam RightContext
    *   The context bound required on the right output of all
    *   [[ContextBifunctor.bimap]]-like operations.
    */
  trait Empty[
    +Self : ValueOf,
    -LeftContext[_],
    -RightContext[_],
  ] extends ContextBifunctor[
      [_, _] =>> Self,
      LeftContext,
      RightContext,
      Nothing,
      Nothing,
    ]:

    override def bimap[
      LeftPost : LeftContext,
      RightPost : RightContext,
    ]
      (transformLeft: Nothing => LeftPost)
      (transformRight: Nothing => RightPost)
      : Self = valueOf[Self]
