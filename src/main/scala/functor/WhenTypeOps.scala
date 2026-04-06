package io.github.sgtswagrid.nonsense
package functor

import scala.reflect.ClassTag

/** The [[when]] operator for [[BoundedFunctorOps]], and its derivatives. */
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
  def when[Type : ClassTag]
    : ConditionalTypeFunctor[Self, Content, Type, Codomain]

  /**
    * Provides a view of this structure that prevents elements of a certain
    * [[Type]] from being modified.
    *
    * @tparam Type
    *   The type of element to not modify.
    *
    * @see
    *   [[BoundedFunctorOps.when]] to filter by predicate rather than type.
    */
  def whenNot[Type : ClassTag]
    : ConditionalNegatedTypeFunctor[Self, Content, Type, Codomain]

  /**
    * Alias for `this.whenType[Type].map(transform)`.
    *
    * @see
    *   [[when]]
    */
  final inline def mapType[Type : ClassTag, Result <: Codomain]
    (using Content <:< Codomain)
    (transform: Type => Result)
    : Self[Result | Content] = when[Type].map(transform)
