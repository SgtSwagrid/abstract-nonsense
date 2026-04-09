package io.github.sgtswagrid.nonsense
package functor.covariant.views

import io.github.sgtswagrid.nonsense.functor.covariant.Functor

/**
  * A functor that maps over two layers of a structure at once. Obtained by
  * calling [[deep]] on a nested functor.
  *
  * @param base
  *   The underlying structure.
  *
  * @tparam Outer
  *   The outer layer of the structure.
  *
  * @tparam Inner
  *   The inner layer of the structure.
  *
  * @tparam Output
  *   The type of value contained within the inner layer of the structure.
  */
final class DeepFunctorView[+Outer[+_], +Inner[+_], +Output]
  (base: Functor[Outer, Functor[Inner, Output]])
  extends Functor[[X] =>> Outer[Inner[X]], Output]:

  override protected inline def mapImpl[Post]
    (transform: Output => Post)
    : Outer[Inner[Post]] = base.map(_.map(transform))

  override def toString = base.toString
