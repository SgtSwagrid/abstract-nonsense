package io.github.sgtswagrid.nonsense
package functor.covariant.views

import io.github.sgtswagrid.nonsense.functor.covariant.{Functor, PartialFunctor}

/**
  * A [[PartialFunctor]] that maps over two layers of a structure at once.
  * Obtained by calling [[functor.covariant.ops.DeepOps.deep deep]] on a nested
  * functor.
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
  *   The upper bound on [[Output]] imposed by the inner layer.
  *
  * @tparam InnerContext
  *   The context bound on [[Output]] imposed by the inner layer.
  *
  * @tparam Output
  *   The type of value contained within the inner layer of the structure.
  */
final class DeepFunctorView[
  +Outer[+_],
  +Inner[+_ <: InnerCodomain],
  -InnerCodomain,
  -InnerContext[_ <: InnerCodomain],
  +Output <: InnerCodomain,
](base: Functor[Outer, PartialFunctor[Inner, InnerCodomain, InnerContext, Output]])
  extends PartialFunctor[[X <: InnerCodomain] =>> Outer[Inner[X]], InnerCodomain, InnerContext, Output]:

  override inline def map[Post <: InnerCodomain : InnerContext]
    (transform: Output => Post)
    : Outer[Inner[Post]] = base.map(_.map(transform))

  override def toString = base.toString
