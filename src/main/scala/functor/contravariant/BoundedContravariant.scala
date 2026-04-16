package io.github.sgtswagrid.nonsense
package functor.contravariant

import io.github.sgtswagrid.nonsense.functor.contravariant.ops.ContravariantWhenOps
import io.github.sgtswagrid.nonsense.functor.contravariant.views.{
  ConditionalContravariantView, NegatedTypeContravariantView,
  TypeContravariantView,
}
import io.github.sgtswagrid.nonsense.util.NoContext
import scala.annotation.unchecked.uncheckedVariance
import scala.reflect.ClassTag

/**
  * ## Bounded Contravariant Functors
  *
  * A bounded contravariant functor is a restricted [[Contravariant]] that can
  * only consume values with certain properties.
  *
  * ### Constraints
  *
  *   1. A lower bound [[Domain]] on the type [[X]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Consumer`).
  *
  * @tparam Domain
  *   The lower bound on [[X]] (e.g. [[Nothing]]).
  *
  * @tparam X
  *   The type of value consumed (e.g. `Event`).
  *
  * @see
  *   If a context bound is needed, use [[ContextContravariant]] or
  *   [[PartialContravariant]] instead.
  */
trait BoundedContravariant[+Self[-_], +Domain, -X >: Domain]
  extends PartialContravariant[Self, Domain, NoContext, X],
          ContravariantWhenOps[Self, Domain, X]:

  override final inline def contrawhen
    (condition: (X @uncheckedVariance) => Boolean)
    : ConditionalContravariantView[Self, Domain, X] =
    ConditionalContravariantView(this, condition)

  override final inline def contrawhen[Active >: Domain : ClassTag]
    : TypeContravariantView[Self, Domain, X, Active] =
    new TypeContravariantView[Self, Domain, X, Active](this)

  override final inline def contraunless[Inactive >: Domain : ClassTag]
    : NegatedTypeContravariantView[Self, Domain, X, Inactive] =
    new NegatedTypeContravariantView[Self, Domain, X, Inactive](this)

object BoundedContravariant:

  /** A [[BoundedContravariant]] that never consumes any value. */
  trait Empty[+Self : ValueOf]
    extends BoundedContravariant[[_] =>> Self, Nothing, Nothing]:

    override def contramap[Y : NoContext](transform: Y => Nothing): Self =
      valueOf[Self]
