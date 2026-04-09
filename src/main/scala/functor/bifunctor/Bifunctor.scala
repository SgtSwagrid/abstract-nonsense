package io.github.sgtswagrid.nonsense
package functor.bifunctor

/**
  * A bifunctor is something that can be mapped over in two different ways.
  *
  * Any implementation need only define [[bimap]], and everything else will be
  * derived from it.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure (e.g.
  *   [[Int]]).
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure (e.g.
  *   [[Int]]).
  *
  * @see
  *   [[io.github.sgtswagrid.nonsense.functor.bivariant.Profunctor]] for a
  *   variant that is contravariant in one of the type parameters.
  */
trait Bifunctor[+Self[+_, +_], +Left, +Right]
  extends BoundedBifunctor[Self, Any, Any, Left, Right]
