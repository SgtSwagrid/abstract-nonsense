package io.github.sgtswagrid.nonsense
package functor

import scala.reflect.ClassTag

/** The [[whenType]] operator for [[BoundedFunctorOps]], and its derivatives. */
trait WhenTypeOps[+Self[+_], +Content, -Codomain]:

  /**
    * Provides a view of this structure that only allows elements of a certain
    * [[Type]] to be modified. All other elements are left as-is.
    *
    * @tparam Type
    *   The type of element to modify.
    *
    * @see
    *   [[BoundedFunctorOps.when]] to filter by predicate rather than type.
    */
  def whenType[Type : ClassTag](using Content <:< Codomain)
    : ConditionalTypeFunctor[Self, Content, Type, Codomain]

  /**
    * Alias for `this.whenType[Type].map(transform)`.
    *
    * @see
    *   [[whenType]]
    */
  final inline def mapType[Type : ClassTag, Result <: Codomain]
    (using Content <:< Codomain)
    (transform: Type => Result)
    : Self[Result | Content] = whenType[Type].map(transform)
