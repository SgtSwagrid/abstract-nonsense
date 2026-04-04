package io.github.sgtswagrid.nonsense
package functor

import scala.reflect.ClassTag

/**
  * A functor that only maps values that satisfy a given condition. Obtained by
  * calling [[BoundedFunctorOps.when]].
  *
  * @param base
  *   The underlying structure.
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  * @tparam Content
  *   The type of value contained in this structure (e.g. [[Int]]).
  * @tparam Active
  *   The subtype of [[Content]] that is being modified.
  * @tparam Codomain
  *   The upper bound on [[Content]] following any [[map]]-like operation.
  */
class ConditionalTypeFunctor[
  +Self[+X],
  +Content,
  +Active : ClassTag,
  -Codomain,
]
  (using cast: Content <:< Codomain)
  (base: BoundedFunctorOps[Self, Content, Codomain])
  extends BoundedFunctorOps[[X] =>> Self[X | Content], Active, Codomain]:

  override def map[Result <: Codomain]
    (transform: Active => Result)
    : Self[Result | Content] = base.map:
    case value: Active => transform(value)
    case value         => value.asInstanceOf[Content & Codomain]

  override def toString = base.toString
