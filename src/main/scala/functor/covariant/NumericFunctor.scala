package io.github.sgtswagrid.nonsense
package functor.covariant

import io.github.sgtswagrid.nonsense.misc.NumericBoolean.given_Numeric_Boolean
import io.github.sgtswagrid.nonsense.transform.{NegateOps, ScaleOps}

/**
  * A restricted [[Functor]] that can only contain [[Numeric]] values.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  *
  * @tparam Output
  *   The type of value contained in this structure (e.g. [[Int]]).
  */
trait NumericFunctor[+Self[+_], +Output : Numeric as Number]
  extends ContextFunctor[Self, Numeric, Output],
          NegateOps[Self[Output]],
          ScaleOps[Self, Output]:

  override inline def negate: Self[Output] = map(Number.negate)

  override inline def scale[Number >: Output : Numeric as Number]
    (factor: Number)
    : Self[Number] = map(Number.times(_, factor))
