package io.github.sgtswagrid.nonsense
package functor.profunctor

import io.github.sgtswagrid.nonsense.functor.contravariant.Contravariant
import io.github.sgtswagrid.nonsense.functor.covariant.Functor

/**
  * ## Profunctors
  *
  * A profunctor is an object with a [[dimap]] operator.
  *
  * They typically represent processing pipelines that transform values.
  *
  * ### Profunctor Laws
  *
  * Please see the corresponding covariant and contravariant functor laws for
  * [[Functor]] and [[Contravariant]] respectively. They should hold here too.
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Function]]).
  *
  * @tparam I
  *   The type of value consumed (e.g. [[Int]]).
  *
  * @tparam O
  *   The type of value produced (e.g. [[Int]]).
  *
  * @see
  *   [[Bifunctor]] is covariant in both type parameters.
  */
trait Profunctor[+Self[-_, +_], -I, +O]
  extends BoundedProfunctor[Self, Nothing, Any, I, O],
          Functor[[o] =>> Self[I, o], O],
          Contravariant[[i] =>> Self[i, O], I]
