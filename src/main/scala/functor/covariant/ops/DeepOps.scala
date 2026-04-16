package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import io.github.sgtswagrid.nonsense.functor.covariant.PartialFunctor
import io.github.sgtswagrid.nonsense.functor.covariant.views.DeepFunctorView

/** The [[deep]] operator for [[functor.covariant.Functor Functor]], and its derivatives. */
trait DeepOps[+Self[+_], +Output]:

  /**
    * Provides a view of this structure that combines the two outermost layers
    * into one, allowing them to be mapped over together.
    *
    * @note
    *   Following any [[functor.covariant.PartialFunctor.map map]]-like operation,
    *   the structure will be returned to its original form. The merger will
    *   thereafter be forgotten.
    *
    * @example
    *   {{{
    * val a = List(List(1, 2), List(3))
    * val b = a.deep.map(_ * 2)
    * // b == List(List(2, 4), List(6))
    * val c = b.map(_.sum)
    * // c == List(6, 6)
    *   }}}
    */
  def deep[Inner[+_ <: C], C, Ctx[_ <: C], InnerOutput <: C]
    (using Output <:< PartialFunctor[Inner, C, Ctx, InnerOutput])
    : DeepFunctorView[Self, Inner, C, Ctx, InnerOutput]

  /**
    * Alias for `this.deep.map(transform)`.
    * @see
    *   [[deep]] for a more general form.
    */
  final inline def deepMap[Inner[+_ <: C], C, Ctx[_ <: C], InnerOutput <: C, InnerPost <: C : Ctx]
    (using Output <:< PartialFunctor[Inner, C, Ctx, InnerOutput])
    (transform: InnerOutput => InnerPost)
    : Self[Inner[InnerPost]] = deep.map(transform)
