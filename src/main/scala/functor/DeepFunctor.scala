package io.github.sgtswagrid.abstractnonsense
package functor

final class DeepFunctor[
  +Outer[+X <: Inner[Codomain]] <: Functor[Outer, Inner[Codomain], X],
  Inner[+X <: Codomain] <: Functor[Inner, Codomain, X],
  Codomain,
  +Content <: Codomain,
]
  (base: Outer[Inner[Content]])
  extends Functor[
    [X <: Codomain] =>> DeepFunctor[Outer, Inner, Codomain, X],
    Codomain,
    Content,
  ]:

  override protected def self = this

  override def map[Result <: Codomain]
    (transform: Content => Result)
    : DeepFunctor[Outer, Inner, Codomain, Result] =
    DeepFunctor(base.map(_.map(transform)))

  def unsquash: Outer[Inner[Content]] = base
