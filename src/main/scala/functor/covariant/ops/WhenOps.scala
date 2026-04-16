package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import io.github.sgtswagrid.nonsense.functor.covariant.views.{
  ConditionalFunctorView, NegatedTypeFunctorView, TypeFunctorView,
}
import io.github.sgtswagrid.nonsense.util.NoContext
import scala.reflect.ClassTag

/**
  * The [[when]] operator for [[functor.covariant.BoundedFunctor]], and its
  * derivatives.
  */
trait WhenOps[
  +Self[+_ <: Codomain],
  -Codomain,
  +ConditionalView,
  +X <: Codomain,
] extends MapOps[Self, Codomain, NoContext, X]:

  /**
    * Provides a view of this structure that only allows elements matching a
    * [[condition]] to be modified. All other elements are left as-is.
    *
    * @param condition
    *   The condition that determines which elements are to be modified.
    *
    * @note
    *   Following any [[map]]-like operation, the structure will be returned to
    *   its original form. The [[condition]] will thereafter be forgotten.
    *
    * @note
    *   Consecutive calls to [[when]] will stack conditions, only allowing
    *   elements to be modified when they match all of them.
    *
    * @example
    *   ```scala
    *   val a = List(1, 2, 3, 4)
    *   val b = a.when(_ % 2 == 0).map(_ + 10)
    *   // a == List(1, 12, 3, 14)
    *   val c = b.map(_ + 100)
    *   // c == List(101, 112, 103, 114)
    *   ```
    */
  def when(condition: X => Boolean): ConditionalView

  /**
    * Provides a view of this structure that only allows elements of a certain
    * [[Active]] type to be modified. All other elements are left as-is.
    *
    * @tparam Active
    *   The subtype of [[X]] that is being modified.
    */
  def when[Active <: Codomain : ClassTag]
    : TypeFunctorView[Self, Codomain, X, Active]

  /**
    * Provides a view of this structure that prevents elements of a certain
    * [[Inactive]] type from being modified.
    *
    * @tparam Inactive
    *   The subtype of [[X]] that is left unmodified.
    */
  def unless[Inactive <: Codomain : ClassTag]
    : NegatedTypeFunctorView[Self, Codomain, X, Inactive]

  /**
    * A version of [[when]] where the input to [[condition]] is provided
    * implicitly as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  inline def whenCtx(condition: X ?=> Boolean): ConditionalView =
    when(value => condition(using value))

  /**
    * Alias for `this.when(_ == value)`.
    *
    * @see
    *   [[when]]
    */
  inline def whenEquals(value: Codomain): ConditionalView = when(_ == value)

  /**
    * Alias for `this.when(transform.isDefinedAt).map(transform)`.
    *
    * @see
    *   [[when]]
    */
  final inline def mapPartial[Post >: X <: Codomain]
    (transform: PartialFunction[X, Post])
    : Self[Post] = map: value =>
    if transform.isDefinedAt(value) then transform(value) else value

  /**
    * Alias for
    * `this.when(transform(transform(_).isDefined).map(transform(_).get)`.
    *
    * @see
    *   [[when]]
    */
  final inline def mapPartial[Post >: X <: Codomain]
    (transform: X => Option[Post])
    : Self[Post] = map: value =>
    transform(value) match
      case Some(transformed) => transformed
      case None              => value
