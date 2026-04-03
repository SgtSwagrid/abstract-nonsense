package io.github.sgtswagrid.nonsense
package functor

import io.github.sgtswagrid.nonsense.caching.Cache
import io.github.sgtswagrid.nonsense.misc.SelfOps
import scala.compiletime.constValue
import scala.compiletime.ops.int.*

/**
  * A restricted [[FunctorOps]] that can only contain particular values.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  * @tparam Content
  *   The type of value contained in this structure (e.g. [[Int]]).
  * @tparam Codomain
  *   The upper bound on types that may be produced by `map`.
  */
trait BoundedFunctorOps[
  +Self[+X], // <: BoundedFunctorOps[Self, X, Codomain],
  +Content,
  -Codomain,
] extends BoundedFunctorKind[Self, Codomain]: // , SelfOps[Self[Content]]:

  /** Transform the contents arbitrarily. */
  def map[Result <: Codomain](transform: Content => Result): Self[Result]

  /**
    * A version of [[map]] where [[transform]] is cached so as to prevent
    * repeated computation for the same input.
    *
    * @note
    *   All outputs of [[transform]] will be kept in memory until the returned
    *   structure is garbage-collected.
    */
  final def mapCached[Result <: Codomain]
    (transform: Content => Result)
    : Self[Result] = map(Cache.memoise(transform))

  /** Ignore the existing value and take a new one instead. */
  final def mapTo[Result <: Codomain](value: Result): Self[Result] =
    map(_ => value)

  /**
    * Ignore the existing value and take a new one instead. [[value]] is
    * evaluated at most once, upon first use.
    */
  final def mapToLazy[Result <: Codomain](value: => Result): Self[Result] =
    lazy val cache = value
    map(_ => cache)

  /**
    * Ignore the existing value and take a new one instead. [[value]] is
    * re-evaluated upon each use.
    */
  final def mapToGenerator[Result <: Codomain](value: => Result): Self[Result] =
    map(_ => value)

  /**
    * A version of [[map]] where the input is provided implicitly as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  final def mapCtx[Result <: Codomain]
    (transform: Content ?=> Result)
    : Self[Result] = map(value => transform(using value))

  /**
    * Apply [[transform]] to the elements matching [[condition]]. All other
    * elements are left as-is.
    */
  final def mapIf[Result >: Content <: Codomain]
    (condition: Content => Boolean)
    (transform: Content => Result)
    : Self[Result] = map: value =>
    if condition(value) then transform(value) else value

  /**
    * Apply [[transform]] to the elements in its domain. All other elements are
    * left as-is.
    */
  final inline def mapPartial[Result >: Content <: Codomain]
    (transform: PartialFunction[Content, Result])
    : Self[Result] = map(value => transform.lift(value).getOrElse(value))

  /**
    * Replace elements matching [[condition]] with [[value]]. All other elements
    * are left as-is.
    */
  final inline def mapToIf[Result >: Content <: Codomain]
    (condition: Content => Boolean)
    (value: Result)
    : Self[Result] = mapIf(condition)(_ => value)

  /** Perform a side effect for each contained value. */
  final inline def forEach
    (using Content <:< Codomain)
    (effect: Content => Any)
    : Self[Content] = map: value =>
    effect(value)
    value.asInstanceOf[Content & Codomain]

  final def eachAsInstanceOf[Result <: Codomain]: Self[Result] =
    asInstanceOf[Self[Result]]

  /**
    * Provides a view of this mappable object that combines the two outermost
    * layers into one, allowing them to be mapped over together.
    *
    * @note
    *   Following any [[map]]-like operation, the structure will be returned to
    *   its original form. The merger will thereafter be forgotten.
    *
    * @example
    *   {{{
    * val a = List(List(1, 2), List(3))
    * val b = a.deep.map(_ * 2)
    * // b == List(List(2, 4), List(6))
    * val c = b.map(_.sum)
    * // c == List(6, 6)
    *   }}}
    */
  final inline def deep[
    Outer[+X] <: BoundedFunctorOps[Outer, X, Inner[SubCodomain]],
    Inner[+X] <: BoundedFunctorOps[Inner, X, SubCodomain],
    SubContent <: SubCodomain,
    SubCodomain,
  ]
    (
      using f: Content <:< (Codomain & Inner[SubContent]),
      g: Self[Inner[SubContent]] <:< Outer[Inner[SubContent]],
    )
    : DeepFunctor[Outer, Inner, SubContent, SubCodomain] =
    DeepFunctor(g(map(f)))

  /**
    * Provides a view of this mappable object that only allows elements matching
    * a [[condition]] to be modified. All other elements are left as-is.
    *
    * @note
    *   Following any [[map]]-like operation, the structure will be returned to
    *   its original form. The [[condition]] will be thereafter be forgotten.
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
  final inline def when
    (using Content <:< Codomain)
    (condition: Content => Boolean)
    : ConditionalFunctor[Self, Content, Codomain] =
    ConditionalFunctor[Self, Content, Codomain](this, condition)

  /**
    * A version of [[when]] where the input is provided implicitly as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  final inline def whenCtx
    (using Content <:< Codomain)
    (condition: Content ?=> Boolean)
    : ConditionalFunctor[Self, Content, Codomain] =
    when(value => condition(using value))
