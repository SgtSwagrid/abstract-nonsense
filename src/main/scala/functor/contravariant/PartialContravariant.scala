package io.github.sgtswagrid.nonsense
package functor.contravariant

import io.github.sgtswagrid.nonsense.functor.contravariant.ops.ContramapToXOps

/**
  * ## Partial Contravariant Functors
  *
  * A partial contravariant functor is a restricted [[Contravariant]] that can
  * only consume values with certain properties.
  *
  * ### Constraints
  *
  *   1. A lower bound [[Domain]] on the type [[X]].
  *   2. A context bound [[Context]] required for the type [[X]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Consumer`).
  *
  * @tparam Domain
  *   The lower bound on [[X]] (e.g. [[Nothing]]).
  *
  * @tparam Context
  *   The context bound on [[X]] (e.g. [[Numeric]]).
  *
  * @tparam X
  *   The type of value consumed (e.g. [[Int]]).
  *
  * @see
  *   If only one of the above constraints is needed, instead use
  *   [[BoundedContravariant]] or [[ContextContravariant]] respectively.
  */
trait PartialContravariant[
  +Self[-_],
  +Domain,
  -Context[_],
  -X >: Domain,
] extends ContramapToXOps[Self, Domain, Context, X]

object PartialContravariant:

  /** A [[PartialContravariant]] that never consumes any value. */
  trait Empty[+Self : ValueOf, -Context[_]]
    extends PartialContravariant[[_] =>> Self, Nothing, Context, Nothing]:

    override def contramap[Y : Context](transform: Y => Nothing): Self =
      valueOf[Self]
