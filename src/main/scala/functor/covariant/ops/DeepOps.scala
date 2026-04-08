package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor
import io.github.sgtswagrid.nonsense.functor.covariant.views.DeepFunctorView

/** The [[deep]] operator for [[BoundedFunctor]], and its derivatives. */
trait DeepOps[+Self[+_], -Codomain, +Output]:

  /**
    * Provides a view of this structure that combines the two outermost layers
    * into one, allowing them to be mapped over together.
    *
    * @note
    *   Following any [[BoundedFunctor.map]]-like operation, the structure will
    *   be returned to its original form. The merger will thereafter be
    *   forgotten.
    * @note
    *   Consecutive calls to [[deep]] will merge more layers together, allowing
    *   for mapping over deeper structures.
    * @example
    *   {{{
    * val a = List(List(1, 2), List(3))
    * val b = a.deep.map(_ * 2)
    * // b == List(List(2, 4), List(6))
    * val c = b.map(_.sum)
    * // c == List(6, 6)
    *   }}}
    */
  def deep[
    Outer[+X] <: BoundedFunctor[Outer, Inner[InnerCodomain], X],
    Inner[+X] <: BoundedFunctor[Inner, InnerCodomain, X],
    InnerCodomain,
    InnerOutput <: InnerCodomain,
  ]
    (
      using Output <:< (Codomain & Inner[InnerOutput]),
      Self[Inner[InnerOutput]] <:< Outer[Inner[InnerOutput]],
    )
    : DeepFunctorView[Outer, Inner, InnerCodomain, InnerOutput]

  /**
    * Alias for `this.deep.map(transform)`.
    * @see
    *   [[deep]]
    */
  final inline def deepMap[
    Outer[+X] <: BoundedFunctor[Outer, Inner[InnerCodomain], X],
    Inner[+X] <: BoundedFunctor[Inner, InnerCodomain, X],
    InnerCodomain,
    InnerOutput <: InnerCodomain,
    InnerResult <: InnerCodomain,
  ]
    (
      using Output <:< (Codomain & Inner[InnerOutput]),
      Self[Inner[InnerOutput]] <:< Outer[Inner[InnerOutput]],
    )
    (transform: InnerOutput => InnerResult)
    : Outer[Inner[InnerResult]] = deep.map(transform)
