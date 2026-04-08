package io.github.sgtswagrid.nonsense
package functor.covariant

import io.github.sgtswagrid.nonsense.functor.covariant.*
import io.github.sgtswagrid.nonsense.functor.covariant.ops.*
import io.github.sgtswagrid.nonsense.functor.covariant.views.*
import scala.reflect.ClassTag

/**
  * A restricted [[Functor]] that can only contain particular values.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  *
  * @tparam Codomain
  *   The upper bound on [[Output]] following any [[map]]-like operation.
  *
  * @tparam Output
  *   The type of value contained in this structure (e.g. [[Int]]).
  */
trait BoundedFunctor[+Self[+_], -Codomain, +Output]
  extends MapToXOps[Self, Codomain, Output],
          WhenOps[Self, Codomain, Output],
          WhenTypeOps[Self, Codomain, Output],
          DeepOps[Self, Codomain, Output],
          NumericFunctorOps[Self, Codomain, Output]:

  override final inline def when
    (using Output <:< Codomain)
    (condition: Output => Boolean)
    : ConditionalFunctorView[Self, Codomain, Output] =
    ConditionalFunctorView[Self, Codomain, Output](this, condition)

  override final inline def when[Type : ClassTag]
    : TypeFunctorView[Self, Codomain, Output, Type] =
    new TypeFunctorView[Self, Codomain, Output, Type](this)

  override final inline def whenNot[Type : ClassTag]
    : NegatedTypeFunctorView[Self, Codomain, Output, Type] =
    new NegatedTypeFunctorView[Self, Codomain, Output, Type](this)

  override final inline def deep[
    Outer[+X] <: BoundedFunctor[Outer, Inner[InnerCodomain], X],
    Inner[+X] <: BoundedFunctor[Inner, InnerCodomain, X],
    InnerCodomain,
    InnerOutput <: InnerCodomain,
  ]
    (
      using f: Output <:< (Codomain & Inner[InnerOutput]),
      g: Self[Inner[InnerOutput]] <:< Outer[Inner[InnerOutput]],
    )
    : DeepFunctorView[Outer, Inner, InnerCodomain, InnerOutput] =
    DeepFunctorView(g(map(f)))
