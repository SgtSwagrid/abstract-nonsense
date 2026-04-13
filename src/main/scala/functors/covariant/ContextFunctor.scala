package io.github.sgtswagrid.nonsense
package functors.covariant

import io.github.sgtswagrid.nonsense.functors.covariant.ops.*

/**
  * A restricted [[Functor]] that can only contain particular values,
  * constrained by context bounds.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  *
  * @tparam Context
  *   The context bound on [[Output]] that must be present following any
  *   [[map]]-like operation.
  *
  * @tparam Output
  *   The type of value contained in this structure (e.g. [[Int]]).
  */
trait ContextFunctor[+Self[+_], -Context[_], +Output]
  extends BoundedContextFunctor[Self, Any, Context, Output]

object ContextFunctor:

  /**
    * A [[ContextFunctor]] that contains no values.
    *
    * @tparam Self
    *   The singleton produced by all [[ContextFunctor.map]]-like operations.
    *
    * @tparam Context
    *   The context bound required on the output of all
    *   [[ContextFunctor.map]]-like operations.
    */
  trait Empty[+Self : ValueOf, -Context[_]]
    extends ContextFunctor[[_] =>> Self, Context, Nothing]:

    override def map[Post : Context](transform: Nothing => Post): Self =
      valueOf[Self]
