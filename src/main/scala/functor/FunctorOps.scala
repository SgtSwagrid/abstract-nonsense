package io.github.sgtswagrid.nonsense
package functor

/**
  * A functor is something that can be mapped over.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  * @tparam Content
  *   The type of value contained in this structure (e.g. [[Int]]).
  */
trait FunctorOps[
  +Self[+X] <: FunctorOps[Self, X],
  +Content,
] extends BoundedFunctorOps[Self, Content, Any]
