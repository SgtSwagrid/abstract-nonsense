package io.github.sgtswagrid.nonsense
package functor.invariant

import io.github.sgtswagrid.nonsense.util.NoContext

/**
  * ## Invariant Functors
  *
  * An invariant functor is an object with an [[xmap]] operator.
  *
  * They typically represent structures that both contain and consume values of
  * the same type simultaneously.
  *
  * ### Invariant Functor Laws
  *
  * The following invariant functor laws should hold in any implementation:
  *
  * #### Identity Law
  * ```scala
  * fa.xmap(identity)(identity) == fa
  * ```
  *
  * #### Composition Law
  * ```scala
  * fa.xmap(f)(g).xmap(h)(k) == fa.xmap(f.andThen(h))(k.andThen(g))
  * ```
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is.
  *
  * @tparam X
  *   The type of value contained (e.g. [[Int]]).
  */
trait Invariant[+Self[_], X]
  extends BoundedInvariant[Self, Nothing, Any, X],
          ContextInvariant[Self, NoContext, X]

object Invariant:

  /** An [[Invariant]] that never contains any value. */
  trait Empty[+Self : ValueOf] extends Invariant[[_] =>> Self, Nothing]:

    override def xmap[Y : NoContext]
      (forward: Nothing => Y)
      (backward: Y => Nothing)
      : Self = valueOf[Self]
