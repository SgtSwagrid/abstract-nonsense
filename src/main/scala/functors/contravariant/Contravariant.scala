package io.github.sgtswagrid.nonsense
package functors.contravariant

/**
  * A contravariant functor is something that can be contramapped over.
  *
  * Any implementation need only define [[contramap]], and everything else will
  * be derived from it.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Sink`).
  *
  * @tparam Input
  *   The type of value that can be handled by this structure (e.g. `Event`).
  */
trait Contravariant[+Self[-_], -Input]
  extends BoundedContravariant[Self, Nothing, Input]
