package io.github.sgtswagrid.nonsense
package functor.invariant.ops

/** The [[xmapTo]] operator for [[BoundedInvariant]], and its derivatives. */
trait XmapToOps[
  +Self[_],
  +Domain,
  -Codomain,
  -Context[_],
  X >: Domain <: Codomain,
] extends XmapOps[Self, Domain, Codomain, Context, X]:

  /**
    * Ignore the existing value and use [[forwardValue]] in the forward
    * direction and [[backwardValue]] in the backward direction.
    */
  final def xmapTo[Y >: Domain <: Codomain : Context]
    (forwardValue: Y)
    (backwardValue: X)
    : Self[Y] = xmap(_ => forwardValue)(_ => backwardValue)

  /**
    * Ignore the existing value and use [[forwardValue]] in the forward
    * direction and [[backwardValue]] in the backward direction.
    *
    * @note
    *   Both [[forwardValue]] and [[backwardValue]] are evaluated at most once,
    *   upon first use.
    */
  final def xmapToLazy[Y >: Domain <: Codomain : Context]
    (forwardValue: => Y)
    (backwardValue: => X)
    : Self[Y] =
    lazy val fwd = forwardValue
    lazy val bwd = backwardValue
    xmap(_ => fwd)(_ => bwd)

  /**
    * Ignore the existing value and use [[forwardValue]] in the forward
    * direction and [[backwardValue]] in the backward direction.
    *
    * @note
    *   Both [[forwardValue]] and [[backwardValue]] are re-evaluated upon every
    *   use.
    */
  final def xmapToGenerator[Y >: Domain <: Codomain : Context]
    (forwardValue: => Y)
    (backwardValue: => X)
    : Self[Y] = xmap(_ => forwardValue)(_ => backwardValue)
