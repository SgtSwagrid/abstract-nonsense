package io.github.sgtswagrid.nonsense
package functor

/**
  * A shape for [[BoundedFunctorOps]].
  *
  * @tparam Self
  *   The kind of structure that this describes (e.g. [[List]]).
  * @tparam Codomain
  *   The upper bound on types that may be produced by `map`.
  */
trait BoundedFunctorKind[
  +Self[+X], // <: BoundedFunctorOps[Self, X, Codomain],
  -Codomain,
]
