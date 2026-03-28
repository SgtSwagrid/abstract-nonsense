package io.github.sgtswagrid.abstractnonsense
package functor

trait FunctorKind[
  +Self[+X <: Codomain] <: Functor[Self, Codomain, X],
  Codomain,
]
