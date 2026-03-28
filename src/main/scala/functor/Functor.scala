package io.github.sgtswagrid.abstractnonsense
package functor

import io.github.sgtswagrid.abstractnonsense.caching.Cache
import scala.compiletime.constValue
import scala.compiletime.ops.int.*

trait Functor[
  +Self[+X <: Codomain] <: Functor[Self, Codomain, X],
  Codomain,
  +Content <: Codomain,
] extends FunctorKind[Self, Codomain]:

  protected def self: Self[Content]

  /** Transform the contents arbitrarily. */
  def map[Result <: Codomain](transform: Content => Result): Self[Result]

  /**
    * Transform the contents arbitrarily, caching each result in case the same
    * value appears multiple times. All cached objects will remain in memory so
    * long as the result does.
    */
  final def mapCached[Result <: Codomain]
    (transform: Content => Result)
    : Self[Result] = map(Cache.memoise(transform))

  /** Ignore the existing value, taking a new one instead. */
  final def mapTo[Result <: Codomain](value: Result): Self[Result] =
    map(_ => value)

  /**
    * Ignore the existing value, taking a new one instead. [[value]] is
    * evaluated at most once, upon first use.
    */
  final def mapToLazy[Result <: Codomain](value: => Result): Self[Result] =
    lazy val cache = value
    map(_ => cache)

  /**
    * Ignore the existing value, taking a new one instead. [[value]] is
    * reevaluated upon each use.
    */
  final def mapToGenerator[Result <: Codomain](value: => Result): Self[Result] =
    map(_ => value)

  /**
    * Produce a new value, with the original value provided as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  final def mapContext[Result <: Codomain]
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
  final def mapPartial[Result >: Content <: Codomain]
    (transform: PartialFunction[Content, Result])
    : Self[Result] = map(value => transform.lift(value).getOrElse(value))

  /**
    * Replace elements matching [[condition]] with [[value]]. All other elements
    * are left as-is.
    */
  final def mapToIf[Result >: Content <: Codomain]
    (condition: Content => Boolean)
    (value: Result)
    : Self[Result] = mapIf(condition)(_ => value)

  /** Perform a side effect for each contained value. */
  final def forEach(effect: Content => Any): Self[Content] = map: value =>
    effect(value)
    value

  /**
    * Provides a view of this mappable object that combines the two outermost
    * layers into one, allowing them to be mapped over together.
    */
  final def squash[
    Inner[+X <: SubCodomain] <: Codomain & Functor[Inner, SubCodomain, X],
    SubCodomain,
    Nested <: SubCodomain,
  ]
    (using f: Content <:< Inner[Nested])
    : DeepFunctor[Self, Inner, SubCodomain, Nested] =
    DeepFunctor[Self, Inner, SubCodomain, Nested](
      asInstanceOf[Self[Inner[Nested]]],
    )

object Functor:

  trait Proper[
    +Self[+X] <: Functor[Self, Any, X],
    +Content,
  ] extends Functor[Self, Any, Content]
