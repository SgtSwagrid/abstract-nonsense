package io.github.sgtswagrid.nonsense
package functor.covariant.views

import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor

/**
  * A functor that maps over two layers of a structure at once. Obtained by
  * calling [[BoundedFunctor.deep]] on a nested functor.
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
  * @tparam InnerCodomain
  *   The upper bound on types that may be produced by `map`.
  *
  * @tparam InnerOutput
  *   The type of value contained within the inner layer of the structure.
  */
final class DeepFunctorView[
  +Outer[+X] <: BoundedFunctor[Outer, Inner[InnerCodomain], X],
  +Inner[+X] <: BoundedFunctor[Inner, InnerCodomain, X],
  -InnerCodomain,
  +InnerOutput,
]
  (base: Outer[Inner[InnerOutput]])
  extends BoundedFunctor[
    [X] =>> Outer[Inner[X]],
    InnerCodomain,
    InnerOutput,
  ]:

  override def map[InnerPost <: InnerCodomain]
    (transform: InnerOutput => InnerPost)
    : Outer[Inner[InnerPost]] = base.map(_.map(transform))

  override def toString = base.toString
