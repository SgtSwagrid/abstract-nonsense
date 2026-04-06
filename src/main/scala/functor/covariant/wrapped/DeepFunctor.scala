package io.github.sgtswagrid.nonsense
package functor.covariant.wrapped

import io.github.sgtswagrid.nonsense.functor.covariant.ops.BoundedFunctorOps

/**
  * A functor that maps over two layers of a structure at once. Obtained by
  * calling [[BoundedFunctorOps.deep]] on a nested functor.
  *
  * @param base
  *   The underlying structure.
  *
  * @tparam Outer
  *   The outer layer of the structure.
  *
  * @tparam Inner
  *   The inner layer of the structure.
  *
  * @tparam Content
  *   The type of value contained within the inner layer of the structure.
  *
  * @tparam Codomain
  *   The upper bound on types that may be produced by `map`.
  */
final class DeepFunctor[
  +Outer[+X] <: BoundedFunctorOps[Outer, X, Inner[Codomain]],
  +Inner[+X] <: BoundedFunctorOps[Inner, X, Codomain],
  +Content,
  -Codomain,
]
  (base: Outer[Inner[Content]])
  extends BoundedFunctorOps[
    [X] =>> Outer[Inner[X]],
    Content,
    Codomain,
  ]:

  override def map[Result <: Codomain]
    (transform: Content => Result)
    : Outer[Inner[Result]] = base.map(_.map(transform))

  override def toString = base.toString
