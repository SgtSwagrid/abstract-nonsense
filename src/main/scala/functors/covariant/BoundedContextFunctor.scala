package io.github.sgtswagrid.nonsense
package functors.covariant

import io.github.sgtswagrid.nonsense.functors.covariant.ops.MapToXOps

/**
  * A restricted [[Functor]] that can only contain particular values,
  * constrained by both type and context bounds.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  *
  * @tparam Codomain
  *   The upper bound on [[Output]] following any [[map]]-like operation.
  *
  * @tparam Context
  *   The context bound on [[Output]] that must be present following any
  *   [[map]]-like operation.
  *
  * @tparam Output
  *   The type of value contained in this structure (e.g. [[Int]]).
  */
trait BoundedContextFunctor[
  +Self[+_],
  -Codomain,
  -Context[_],
  +Output <: Codomain,
] extends MapToXOps[Self, Codomain, Context, Output]

object BoundedContextFunctor:

  /**
    * A [[BoundedContextFunctor]] that contains no values.
    *
    * @tparam Self
    *   The singleton produced by all [[BoundedContextFunctor.map]]-like
    *   operations.
    *
    * @tparam Codomain
    *   The upper bound on the output of all [[BoundedContextFunctor.map]]-like
    *   operations.
    *
    * @tparam Context
    *   The context bound required on the output of all
    *   [[BoundedContextFunctor.map]]-like operations.
    */
  trait Empty[+Self : ValueOf, -Codomain, -Context[_]]
    extends BoundedContextFunctor[[_] =>> Self, Codomain, Context, Nothing]:

    override def map[Post <: Codomain : Context]
      (transform: Nothing => Post)
      : Self = valueOf[Self]
