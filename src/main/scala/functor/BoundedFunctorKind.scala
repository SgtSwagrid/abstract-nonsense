package io.github.sgtswagrid.nonsense
package functor

/**
  * A shape for [[BoundedFunctorOps]].
  *
  * @tparam Self
  *   The kind of structure that this describes (e.g. [[List]]).
  * @tparam Codomain
  *   The upper bound on `Content` following any [[BoundedFunctorOps.map]]-like
  *   operation.
  */
trait BoundedFunctorKind[+Self[+_], -Codomain]
