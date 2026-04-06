package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import io.github.sgtswagrid.nonsense.functor.covariant.wrapped.DeepFunctor

/** The [[deep]] operator for [[BoundedFunctorOps]], and its derivatives. */
trait DeepOps[+Self[+_], +Content, -Codomain]:

  /**
    * Provides a view of this structure that combines the two outermost layers
    * into one, allowing them to be mapped over together.
    *
    * @note
    *   Following any [[BoundedFunctorOps.map]]-like operation, the structure
    *   will be returned to its original form. The merger will thereafter be
    *   forgotten.
    *
    * @note
    *   Consecutive calls to [[deep]] will merge more layers together, allowing
    *   for mapping over deeper structures.
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
  def deep[
    Outer[+X] <: BoundedFunctorOps[Outer, X, Inner[SubCodomain]],
    Inner[+X] <: BoundedFunctorOps[Inner, X, SubCodomain],
    SubContent <: SubCodomain,
    SubCodomain,
  ]
    (
      using Content <:< (Codomain & Inner[SubContent]),
      Self[Inner[SubContent]] <:< Outer[Inner[SubContent]],
    )
    : DeepFunctor[Outer, Inner, SubContent, SubCodomain]

  /**
    * Alias for `this.deep.map(transform)`.
    * @see
    *   [[deep]]
    */
  final inline def deepMap[
    Outer[+X] <: BoundedFunctorOps[Outer, X, Inner[SubCodomain]],
    Inner[+X] <: BoundedFunctorOps[Inner, X, SubCodomain],
    SubContent <: SubCodomain,
    SubCodomain,
    Result <: SubCodomain,
  ]
    (
      using Content <:< (Codomain & Inner[SubContent]),
      Self[Inner[SubContent]] <:< Outer[Inner[SubContent]],
    )
    (transform: SubContent => Result)
    : Outer[Inner[Result]] = deep.map(transform)
