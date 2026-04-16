package io.github.sgtswagrid.nonsense
package functor.contravariant

/**
  * ## Context Contravariant Functors
  *
  * A context contravariant functor is a restricted [[Contravariant]] that can
  * only consume values with certain properties.
  *
  * ### Constraints
  *
  *   1. A context bound [[Context]] required for the type [[X]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Consumer`).
  *
  * @tparam Context
  *   The context bound on [[X]] (e.g. [[Numeric]]).
  *
  * @tparam X
  *   The type of value consumed (e.g. [[Int]]).
  *
  * @see
  *   If a lower bound is needed, use [[BoundedContravariant]] or
  *   [[PartialContravariant]] instead.
  */
trait ContextContravariant[+Self[-_], -Context[_], -X]
  extends PartialContravariant[Self, Nothing, Context, X]

object ContextContravariant:

  /** A [[ContextContravariant]] that never consumes any value. */
  trait Empty[+Self : ValueOf, -Context[_]]
    extends ContextContravariant[[_] =>> Self, Context, Nothing]:

    override def contramap[Y : Context](transform: Y => Nothing): Self =
      valueOf[Self]
