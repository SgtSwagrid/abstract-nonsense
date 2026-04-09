package io.github.sgtswagrid.nonsense
package interval

import io.github.sgtswagrid.nonsense.functor.covariant.NumericFunctor
import io.github.sgtswagrid.nonsense.misc.NumericNothing.given_Numeric_Nothing

sealed trait Endpoint[+X : Numeric] extends NumericFunctor[Endpoint, X]

object Endpoint:

  sealed trait LowerBound[+X : Numeric] extends Endpoint[X]

  sealed trait UpperBound[+X : Numeric] extends Endpoint[X]

  sealed trait Discrete[+X : Numeric]
    extends Endpoint[X], NumericFunctor[Discrete, X]

  sealed trait Finite[+X : Numeric]
    extends Endpoint[X], NumericFunctor[Finite, X]

  sealed trait Infinite
    extends Discrete[Nothing], NumericFunctor[[_] =>> Infinite, Nothing]

  sealed trait Open[+X : Numeric] extends Endpoint[X], NumericFunctor[Open, X]

  sealed trait FiniteOpen[+X : Numeric]
    extends Finite[X], Open[X], NumericFunctor[FiniteOpen, X]

  case object NegativeInfinity
    extends Endpoint[Nothing],
            LowerBound[Nothing],
            Infinite,
            NumericFunctor[[_] =>> NegativeInfinity.type, Nothing]:

    override inline def map[Y : Numeric]
      (f: Nothing => Y)
      : NegativeInfinity.type = this

  case object PositiveInfinity
    extends Endpoint[Nothing],
            UpperBound[Nothing],
            Infinite,
            NumericFunctor[[_] =>> PositiveInfinity.type, Nothing]:

    override inline def map[Y : Numeric]
      (f: Nothing => Y)
      : PositiveInfinity.type = this

  case class After[+X : Numeric]
    (value: X)
    extends LowerBound[X], FiniteOpen[X], NumericFunctor[After, X]:

    override inline def map[Y : Numeric](f: X => Y): After[Y] = After(f(value))

  case class Before[+X : Numeric]
    (value: X)
    extends UpperBound[X], FiniteOpen[X], NumericFunctor[Before, X]:

    override inline def map[Y : Numeric](f: X => Y): Before[Y] =
      Before(f(value))

  case class Closed[+X : Numeric]
    (value: X)
    extends LowerBound[X],
            UpperBound[X],
            Discrete[X],
            Finite[X],
            NumericFunctor[Closed, X]:

    override inline def map[Y : Numeric](f: X => Y): Closed[Y] =
      Closed(f(value))
