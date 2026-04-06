package io.github.sgtswagrid.nonsense
package functor.wrapped

import io.github.sgtswagrid.nonsense.functor.ops.BoundedFunctorOps
import scala.compiletime.ops.boolean.!
import scala.reflect.ClassTag
import javax.swing.UIDefaults.ActiveValue

/**
  * A functor that only maps values that satisfy a given condition. Obtained by
  * calling [[BoundedFunctorOps.when]].
  *
  * @param base
  *   The underlying structure.
  * @param condition
  *   The condition that determines which elements are to be modified.
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  * @tparam Content
  *   The type of value contained in this structure (e.g. [[Int]]).
  * @tparam Codomain
  *   The upper bound on [[Content]] following any [[map]]-like operation.
  */
final class ConditionalFunctor[+Self[+X], +Content, -Codomain]
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
    : Self[Result | Content] = base.map:
    case value: Content if condition(value) => transform(value)
    case value => value.asInstanceOf[Content & Codomain]

  override def toString = base.toString
