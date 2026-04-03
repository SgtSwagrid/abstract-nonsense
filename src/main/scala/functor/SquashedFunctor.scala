package io.github.sgtswagrid.nonsense
package functor

final class SquashedFunctor[
  +Outer[+X] <: BoundedFunctorOps[Outer, X, Inner[Codomain]],
  +Inner[+X] <: BoundedFunctorOps[Inner, X, Codomain],
  +Content,
  -Codomain,
]
  (base: Outer[Inner[Content]])
  extends BoundedFunctorOps[
    [X] =>> SquashedFunctor[Outer, Inner, X, Codomain],
    Content,
    Codomain,
  ]:

  override def map[Result <: Codomain]
    (transform: Content => Result)
    : SquashedFunctor[Outer, Inner, Result, Codomain] =
    SquashedFunctor(base.map(_.map(transform)))

  /**
    * Separates the two outermost layers of this mappable object, returning the
    * structure to its original form.
    */
  def unsquash: Outer[Inner[Content]] = base

  override def toString = base.toString
