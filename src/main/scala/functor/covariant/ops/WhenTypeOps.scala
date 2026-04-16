package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import io.github.sgtswagrid.nonsense.functor.covariant.views.{
  NegatedTypeFunctorView, TypeFunctorView,
}
import scala.reflect.ClassTag

/** The [[when]] operator for [[BoundedFunctor]], and its derivatives. */
trait WhenTypeOps[
  +Self[+_ <: Codomain],
  -Codomain,
  +Output <: Codomain,
]:

  /**
    * Provides a view of this structure that only allows elements of a certain
    * [[Active]] type to be modified. All other elements are left as-is.
    *
    * @tparam Active
    *   The subtype of [[Output]] that is being modified.
    */
  def when[Active <: Codomain : ClassTag]
    : TypeFunctorView[Self, Codomain, Output, Active]

  /**
    * Provides a view of this structure that prevents elements of a certain
    * [[Inactive]] type from being modified.
    *
    * @tparam Inactive
    *   The subtype of [[Output]] that is left unmodified.
    */
  def unless[Inactive <: Codomain : ClassTag]
    : NegatedTypeFunctorView[Self, Codomain, Output, Inactive]
