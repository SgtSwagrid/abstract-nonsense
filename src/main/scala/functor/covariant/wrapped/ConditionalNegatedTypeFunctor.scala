package io.github.sgtswagrid.nonsense
package functor.covariant.wrapped

import io.github.sgtswagrid.nonsense.functor.covariant.ops.BoundedFunctorOps
import scala.reflect.ClassTag

/**
  * A functor that only maps values that don't have a certain type. Obtained by
  * calling [[BoundedFunctorOps.when]].
  *
  * @param base
  *   The underlying structure.
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  * @tparam Content
  *   The type of value contained in this structure (e.g. [[Int]]).
  * @tparam Inactive
  *   The subtype of [[Content]] that is left unmodified.
  * @tparam Codomain
  *   The upper bound on [[Content]] following any [[map]]-like operation.
  */
final class ConditionalNegatedTypeFunctor[
  +Self[+X],
  +Content,
  +Inactive : ClassTag,
  -Codomain,
]
  (base: BoundedFunctorOps[Self, Content, Codomain])
  extends BoundedFunctorOps[
    [X] =>> Self[X | Inactive],
    Content,
    Codomain,
  ]:

  override def map[Result <: Codomain]
    (transform: Content => Result)
    : Self[Result | Inactive] = base.map:
    case value: Inactive => value.asInstanceOf[Inactive & Codomain]
    case value           => transform(value)

  override def toString = base.toString
