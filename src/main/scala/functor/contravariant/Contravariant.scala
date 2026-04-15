package io.github.sgtswagrid.nonsense
package functor.contravariant

/**
  * ## Contravariant Functors
  *
  * A contravariant functor is an object with a [[contramap]] operator.
  *
  * They typically represent structures that consume values.
  *
  * ### Contravariant Functor Laws
  *
  * The following contravariant functor laws should hold in any implementation:
  *
  * #### Identity Law
  * ```scala
  * fa.contramap(identity) == fa
  * ```
  *
  * #### Composition Law
  * ```scala
  * fa.contramap(f).contramap(g) == fa.contramap(g.andThen(f))
  * ```
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Consumer`).
  *
  * @tparam X
  *   The type of value consumed (e.g. `Event`).
  */
trait Contravariant[+Self[-_], -X]
  extends BoundedContravariant[Self, Nothing, X]
