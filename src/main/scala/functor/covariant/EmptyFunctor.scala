package io.github.sgtswagrid.nonsense
package functor.covariant

/**
  * A functor that contains no values.
  *
  * @tparam Self
  *   The singleton that is produced by all [[map]]-like operations.
  */
trait EmptyFunctor[+Self : ValueOf] extends Functor[[_] =>> Self, Nothing]:

  override final inline def map[Post](transform: Nothing => Post): Self =
    valueOf[Self]
