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
trait BoundedFunctor[+Self[+_], -Codomain, +Output <: Codomain]
  extends MapToXOps[Self, Codomain, Output],
          WhenOps[Self, Codomain, Output],
          WhenTypeOps[Self, Codomain, Output],
          NumericFunctorOps[Self, Codomain, Output]:

  override final inline def when
    (using Output <:< Codomain)
    (condition: Output => Boolean)
    : ConditionalFunctorView[Self, Codomain, Output] =
    ConditionalFunctorView(this, condition)

  override final inline def when[Active <: Codomain : ClassTag]
    : TypeFunctorView[Self, Codomain, Output, Active] =
    new TypeFunctorView[Self, Codomain, Output, Active](this)

  override final inline def whenNot[Inactive <: Codomain : ClassTag]
    : NegatedTypeFunctorView[Self, Codomain, Output, Inactive] =
    new NegatedTypeFunctorView[Self, Codomain, Output, Inactive](this)
