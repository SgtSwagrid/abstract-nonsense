package io.github.sgtswagrid.nonsense
package interval

import io.github.sgtswagrid.nonsense.functor.covariant.ContextFunctor
import io.github.sgtswagrid.nonsense.misc.NumericNothing.given_Numeric_Nothing
import io.github.sgtswagrid.nonsense.transform.{NegateOps, ScaleOps}
import scala.math.Numeric.Implicits.infixNumericOps
import scala.math.Ordered.orderingToOrdered

sealed trait Endpoint[+X : Numeric]
  extends ContextFunctor[Endpoint, Numeric, X],
          NegateOps[Endpoint[X]],
          ScaleOps[Endpoint, X]:

  def valueOption: Option[X]

object Endpoint:

  sealed trait LowerBound[+X : Numeric]
    extends Endpoint[X], NegateOps[UpperBound[X]]

  sealed trait UpperBound[+X : Numeric]
    extends Endpoint[X], NegateOps[LowerBound[X]]

  sealed trait Discrete[+X : Numeric]
    extends Endpoint[X],
            ContextFunctor[Discrete, Numeric, X],
            NegateOps[Discrete[X]],
            ScaleOps[Discrete, X]

  sealed trait Finite[+X : Numeric]
    extends Endpoint[X],
            ContextFunctor[Finite, Numeric, X],
            NegateOps[Finite[X]],
            ScaleOps[Finite, X]:

    def value: X

    override inline def valueOption: Some[X] = Some(value)

  sealed trait Infinite
    extends Discrete[Nothing],
            ContextFunctor[[_] =>> Infinite, Numeric, Nothing],
            NegateOps[Infinite],
            ScaleOps[[_] =>> Infinite, Nothing]:

    override inline def valueOption: None.type = None

    override inline def scale[Y : Numeric as Number](factor: Y): Infinite =
      if factor >= Number.zero then this else negate

  sealed trait Open[+X : Numeric]
    extends Endpoint[X],
            ContextFunctor[Open, Numeric, X],
            NegateOps[Open[X]],
            ScaleOps[Open, X]

  sealed trait FiniteOpen[+X : Numeric]
    extends Finite[X],
            Open[X],
            ContextFunctor[FiniteOpen, Numeric, X],
            NegateOps[FiniteOpen[X]],
            ScaleOps[FiniteOpen, X]:

    override inline def scale[Y >: X : Numeric as Number]
      (factor: Y)
      : FiniteOpen[Y] =
      if factor >= Number.zero then map(Number.times(_, factor))
      else map(Number.times(_, -factor)).negate

  case object NegativeInfinity
    extends Endpoint[Nothing],
            LowerBound[Nothing],
            Infinite,
            ContextFunctor[
              [_] =>> NegativeInfinity.type,
              Numeric,
              Nothing,
            ],
            NegateOps[PositiveInfinity.type]:

    override inline def map[Y : Numeric]
      (f: Nothing => Y)
      : NegativeInfinity.type = this

    override inline def negate: PositiveInfinity.type = PositiveInfinity

  case object PositiveInfinity
    extends Endpoint[Nothing],
            UpperBound[Nothing],
            Infinite,
            ContextFunctor[
              [_] =>> PositiveInfinity.type,
              Numeric,
              Nothing,
            ],
            NegateOps[NegativeInfinity.type]:

    override inline def map[Y : Numeric]
      (f: Nothing => Y)
      : PositiveInfinity.type = this

    override inline def negate: NegativeInfinity.type = NegativeInfinity

  case class After[+X : Numeric]
    (value: X)
    extends LowerBound[X],
            FiniteOpen[X],
            ContextFunctor[After, Numeric, X],
            NegateOps[Before[X]]:

    override inline def map[Y : Numeric](f: X => Y): After[Y] = After(f(value))

    override inline def negate: Before[X] = Before(-value)

  case class Before[+X : Numeric]
    (value: X)
    extends UpperBound[X],
            FiniteOpen[X],
            ContextFunctor[Before, Numeric, X],
            NegateOps[After[X]]:

    override inline def map[Y : Numeric](f: X => Y): Before[Y] =
      Before(f(value))

    override inline def negate: After[X] = After(-value)

  case class Closed[+X : Numeric]
    (value: X)
    extends LowerBound[X],
            UpperBound[X],
            Discrete[X],
            Finite[X],
            ContextFunctor[Closed, Numeric, X],
            NegateOps[Closed[X]],
            ScaleOps[Closed, X]:

    override inline def map[Y : Numeric](f: X => Y): Closed[Y] =
      Closed(f(value))

    override inline def negate: Closed[X] = Closed(-value)

    override inline def scale[Y >: X : Numeric as Number]
      (factor: Y)
      : Closed[Y] = map(Number.times(_, factor))
