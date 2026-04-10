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
