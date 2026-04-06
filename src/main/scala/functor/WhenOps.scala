package io.github.sgtswagrid.nonsense
package functor

/** The [[when]] operator for [[BoundedFunctorOps]], and its derivatives. */
trait WhenOps[+Self[+_], +Content, -Codomain]
  extends MapOps[Self, Content, Codomain]:

  /**
    * Provides a view of this structure that only allows elements matching a
    * [[condition]] to be modified. All other elements are left as-is.
    *
    * @param condition
    *   The condition that determines which elements are to be modified.
    *
    * @note
    *   Following any [[BoundedFunctorOps.map]]-like operation, the structure
    *   will be returned to its original form. The [[condition]] will thereafter
    *   be forgotten.
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
    *
    * @see
    * [[BoundedFunctorOps.when]] to filter by type rather than predicate.
    */
  def when(using Content <:< Codomain)(condition: Content => Boolean)
    : ConditionalFunctor[Self, Content, Codomain]

  /**
    * A version of [[when]] where the input to [[condition]] is provided
    * implicitly as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  final inline def whenCtx
    (using Content <:< Codomain)
    (condition: Content ?=> Boolean)
    : ConditionalFunctor[Self, Content, Codomain] =
    when(value => condition(using value))

  /**
    * Alias for `this.when(_ == value)`.
    *
    * @see
    *   [[when]]
    */
  final inline def whenEqualTo
    (using Content <:< Codomain)
    (value: Codomain)
    : ConditionalFunctor[Self, Content, Codomain] = when(_ == value)

  /**
    * Alias for `this.when(condition).map(transform)`.
    *
    * @see
    *   [[when]]
    */
  final def mapWhen[Result >: Content <: Codomain]
    (condition: Content => Boolean)
    (transform: Content => Result)
    : Self[Result] = when(condition).map(transform)

  /**
    * Alias for `this.when(transform.isDefinedAt).map(transform)`.
    *
    * @see
    *   [[when]]
    */
  final inline def mapPartial[Result >: Content <: Codomain]
    (transform: PartialFunction[Content, Result])
    : Self[Result] = when(transform.isDefinedAt).map(transform)

  /**
    * Alias for
    * `this.when(transform(transform(_).isDefined).map(transform(_).get)`.
    *
    * @see
    *   [[when]]
    */
  final inline def mapWhenSome[Result >: Content <: Codomain]
    (transform: Content => Option[Result])
    : Self[Result] = map: value =>
    transform(value) match
      case Some(transformed) => transformed
      case None              => value
