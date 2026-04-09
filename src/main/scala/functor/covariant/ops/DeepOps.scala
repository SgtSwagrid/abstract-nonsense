package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import io.github.sgtswagrid.nonsense.functor.covariant.Functor
import io.github.sgtswagrid.nonsense.functor.covariant.views.DeepFunctorView

/** The [[deep]] operator for [[BoundedFunctor]], and its derivatives. */
trait DeepOps[+Self[+_], +Output]:

  /**
    * Provides a view of this structure that combines the two outermost layers
    * into one, allowing them to be mapped over together.
    *
    * @note
    *   Following any [[Functor.map]]-like operation, the structure will be
    *   returned to its original form. The merger will thereafter be forgotten.
    *
    * @note
    *   Consecutive calls to [[deep]] will merge more layers together, allowing
    *   for mapping over even deeper structures. A chain of `n` calls to
    *   [[deep]] will merge the outermost `n + 1` layers.
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
  def deep[Inner[+_], InnerOutput](using Output <:< Functor[Inner, InnerOutput])
    : DeepFunctorView[Self, Inner, InnerOutput]

  /**
    * Alias for `this.deep.map(transform)`.
    * @see
    *   [[deep]] for a more general form.
    */
  final inline def deepMap[Inner[+_], InnerOutput, InnerPost]
    (using Output <:< Functor[Inner, InnerOutput])
    (transform: InnerOutput => InnerPost)
    : Self[Inner[InnerPost]] = deep.map(transform)
