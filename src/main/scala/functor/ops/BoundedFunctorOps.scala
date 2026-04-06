package io.github.sgtswagrid.nonsense
package functor.ops

import io.github.sgtswagrid.nonsense.functor.*
import io.github.sgtswagrid.nonsense.functor.wrapped.*

import scala.reflect.ClassTag

/**
  * A restricted [[FunctorOps]] that can only contain particular values.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  * @tparam Content
  *   The type of value contained in this structure (e.g. [[Int]]).
  * @tparam Codomain
  *   The upper bound on [[Content]] following any [[map]]-like operation.
  */
trait BoundedFunctorOps[+Self[+_], +Content, -Codomain]
  extends BoundedFunctorKind[Self, Codomain],
          MapToXOps[Self, Content, Codomain],
          DeepOps[Self, Content, Codomain],
          WhenOps[Self, Content, Codomain],
          WhenTypeOps[Self, Content, Codomain]:

  override final inline def deep[
    Outer[+X] <: BoundedFunctorOps[Outer, X, Inner[SubCodomain]],
    Inner[+X] <: BoundedFunctorOps[Inner, X, SubCodomain],
    SubContent <: SubCodomain,
    SubCodomain,
  ]
    (
      using f: Content <:< (Codomain & Inner[SubContent]),
      g: Self[Inner[SubContent]] <:< Outer[Inner[SubContent]],
    )
    : DeepFunctor[Outer, Inner, SubContent, SubCodomain] =
    DeepFunctor(g(map(f)))

  override final inline def when
    (using Content <:< Codomain)
    (condition: Content => Boolean)
    : ConditionalFunctor[Self, Content, Codomain] =
    ConditionalFunctor[Self, Content, Codomain](this, condition)

  override final inline def when[Type : ClassTag]
    : ConditionalTypeFunctor[Self, Content, Type, Codomain] =
    new ConditionalTypeFunctor[Self, Content, Type, Codomain](this)

  override final inline def whenNot[Type : ClassTag]
    : ConditionalNegatedTypeFunctor[Self, Content, Type, Codomain] =
    new ConditionalNegatedTypeFunctor[Self, Content, Type, Codomain](this)
