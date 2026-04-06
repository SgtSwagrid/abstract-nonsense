package io.github.sgtswagrid.nonsense
package functor.ops

/** The [[mapTo]] operator for [[BoundedFunctorOps]], and its derivatives. */
trait MapToOps[+Self[+_], +Content, -Codomain]
  extends MapOps[Self, Content, Codomain]:

  /** Ignore the existing value and take a new [[value]] instead. */
  final def mapTo[Result <: Codomain](value: Result): Self[Result] =
    map(_ => value)

  /**
    * Ignore the existing value and take a new [[value]] instead.
    *
    * @note
    *   [[value]] is evaluated at most once, upon first use.
    */
  final def mapToLazy[Result <: Codomain](value: => Result): Self[Result] =
    lazy val cache = value
    map(_ => cache)

  /**
    * Ignore the existing value and take a new [[value]] instead.
    *
    * @note
    *   [[value]] is re-evaluated upon every use.
    */
  final def mapToGenerator[Result <: Codomain](value: => Result): Self[Result] =
    map(_ => value)
