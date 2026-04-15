package io.github.sgtswagrid.nonsense
package functor.bifunctor

/**
  * A restricted [[Bifunctor]] that can only contain particular values on both
  * sides, constrained by context bounds.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam Context
  *   The joint context bound on [[Left]] and [[Right]] that must be present
  *   following any [[bimap]]-like operation.
  *
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure.
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure.
  */
trait ContextBifunctor[+Self[+_, +_], -Context[_], +Left, +Right]
  extends BoundedContextBifunctor[Self, Any, Any, Context, Left, Right]

object ContextBifunctor:

  /**
    * A [[ContextBifunctor]] with symmetric constraints. That is, both sides
    * have the same requirements.
    */
  type Symmetric[+Self[+_, +_], -Context[_], +Left, +Right] =
    ContextBifunctor[Self, Context, Left, Right]

  /**
    * A [[ContextBifunctor]] that contains no values.
    *
    * @tparam Self
    *   The singleton produced by all [[ContextBifunctor.bimap]]-like
    *   operations.
    *
    * @tparam Context
    *   The joint context bound required on the outputs of all
    *   [[ContextBifunctor.bimap]]-like operations.
    */
  trait Empty[+Self : ValueOf, -Context[_]]
    extends ContextBifunctor[[_, _] =>> Self, Context, Nothing, Nothing]:

    override def bimap[LeftPost, RightPost]
      (using Context[LeftPost | RightPost])
      (transformLeft: Nothing => LeftPost)
      (transformRight: Nothing => RightPost)
      : Self = valueOf[Self]
