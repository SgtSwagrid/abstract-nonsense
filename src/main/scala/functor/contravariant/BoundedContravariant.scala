package io.github.sgtswagrid.nonsense
package functor.contravariant

import io.github.sgtswagrid.nonsense.util.NoContext

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
  extends PartialContravariant[Self, Domain, NoContext, X]

object BoundedContravariant:

  /** A [[BoundedContravariant]] that never consumes any value. */
  trait Empty[+Self : ValueOf]
    extends BoundedContravariant[[_] =>> Self, Nothing, Nothing]:

    override def contramap[Y : NoContext](transform: Y => Nothing): Self =
      valueOf[Self]
