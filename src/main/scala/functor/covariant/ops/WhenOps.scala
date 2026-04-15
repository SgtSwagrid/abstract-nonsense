package io.github.sgtswagrid.nonsense
package functor.covariant.ops

import io.github.sgtswagrid.nonsense.functor.covariant.ContextFunctor
import io.github.sgtswagrid.nonsense.functor.covariant.views.ConditionalFunctorView

/** The [[when]] operator for [[ContextFunctor]], and its derivatives. */
trait WhenOps[+Self[+_], -Codomain, +Output <: Codomain]
  extends MapOps[
    Self,
    Codomain,
    [_] =>> DummyImplicit,
    Output,
  ]:

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
    *   {{{
    * val a = List(1, 2, 3, 4)
    * val b = a.when(_ % 2 == 0).map(_ + 10)
    * // a == List(1, 12, 3, 14)
    * val c = b.map(_ + 100)
    * // c == List(101, 112, 103, 114)
    *   }}}
    */
  def when(condition: Output => Boolean)
    : ConditionalFunctorView[Self, Codomain, Output]

  /**
    * A version of [[when]] where the input to [[condition]] is provided
    * implicitly as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  final inline def whenCtx
    (condition: Output ?=> Boolean)
    : ConditionalFunctorView[Self, Codomain, Output] =
    when(value => condition(using value))

  /**
    * Alias for `this.when(_ == value)`.
    *
    * @see
    *   [[when]]
    */
  final inline def whenEqualTo
    (value: Codomain)
    : ConditionalFunctorView[Self, Codomain, Output] = when(_ == value)

  /**
    * Alias for `this.when(condition).map(transform)`.
    *
    * @see
    *   [[when]]
    */
  final def mapWhen[Post >: Output <: Codomain]
    (condition: Output => Boolean)
    (transform: Output => Post)
    : Self[Post] = when(condition).map(transform)

  /**
    * Alias for `this.when(transform.isDefinedAt).map(transform)`.
    *
    * @see
    *   [[when]]
    */
  final inline def mapPartial[Post >: Output <: Codomain]
    (transform: PartialFunction[Output, Post])
    : Self[Post] = when(transform.isDefinedAt).map(transform)

  /**
    * Alias for
    * `this.when(transform(transform(_).isDefined).map(transform(_).get)`.
    *
    * @see
    *   [[when]]
    */
  final inline def mapPartial[Post >: Output <: Codomain]
    (transform: Output => Option[Post])
    : Self[Post] = map: value =>
    transform(value) match
      case Some(transformed) => transformed
      case None              => value
