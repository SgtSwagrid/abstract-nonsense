package io.github.sgtswagrid.nonsense
package functor

class ConditionalFunctor[+Self[+X], +Content, -Codomain]
  (using cast: Content <:< Codomain)
  (
    base: BoundedFunctorOps[Self, Content, Codomain],
    condition: Content => Boolean,
  )
  extends BoundedFunctorOps[
    [X] =>> Self[X | Content],
    Content,
    Codomain,
  ]:

  override def map[Result <: Codomain]
    (transform: Content => Result)
    : Self[Result | Content] = base.map: value =>
    if condition(value) then transform(value)
    else value.asInstanceOf[Content & Codomain]
