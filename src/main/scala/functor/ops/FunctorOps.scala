package io.github.sgtswagrid.nonsense
package functor.ops

/**
  * A functor is something that can be mapped over.
  *
  * Any implementation need only define [[map]], and everything else will be
  * derived from it.
  *
  * Take care to ensure that the following functor laws hold:
  *
  *   1. Identity: `fa.map(identity) == fa`
  *   2. Composition: `fa.map(f).map(g) == fa.map(f.andThen(g))`
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
