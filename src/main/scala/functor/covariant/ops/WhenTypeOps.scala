package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import io.github.sgtswagrid.nonsense.functor.covariant.views.{
  NegatedTypeFunctorView, TypeFunctorView,
}
import scala.reflect.ClassTag

/** The [[when]] operator for [[BoundedFunctor]], and its derivatives. */
trait WhenTypeOps[+Self[+_], +Output, -Codomain]:

  /**
    * Provides a view of this structure that only allows elements of a certain
    * [[Active] type to be modified. All other elements are left as-is. @tparam Active The subtype of [[Output]]
    * that is being modified.
    *
    * @see
    *   [[BoundedFunctor.when]] to filter by predicate rather than type.
    */
  def when[Active : ClassTag]: TypeFunctorView[Self, Codomain, Output, Active]

  /**
    * Provides a view of this structure that prevents elements of a certain
    * [[Inactive]] type from being modified.
    *
    * @tparam Inactive
    *   The subtype of [[Output]] that is left unmodified.
    *
    * @see
    *   [[BoundedFunctor.when]] to filter by predicate rather than type.
    */
  def whenNot[Inactive : ClassTag]
    : NegatedTypeFunctorView[Self, Codomain, Output, Inactive]

  /**
    * Alias for `this.whenType[Type].map(transform)`.
    *
    * @see
    *   [[when]]
    */
  final inline def mapType[Active : ClassTag, Post <: Codomain]
    (using Output <:< Codomain)
    (transform: Active => Post)
    : Self[Post | Output] = when[Active].map(transform)
