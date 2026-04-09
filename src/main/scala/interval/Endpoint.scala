package io.github.sgtswagrid.nonsense
package interval

import io.github.sgtswagrid.nonsense.functor.covariant.{EmptyFunctor, Functor}

sealed trait Endpoint[+X] extends Functor[Endpoint, X]

object Endpoint:

  sealed trait LowerBound[+X] extends Endpoint[X]

  sealed trait UpperBound[+X] extends Endpoint[X]

  sealed trait Discrete[+X] extends Endpoint[X], Functor[Discrete, X]

  sealed trait Finite[+X] extends Endpoint[X], Functor[Finite, X]

  sealed trait Infinite
    extends Discrete[Nothing], Functor[[_] =>> Infinite, Nothing]

  sealed trait Open[+X] extends Endpoint[X], Functor[Open, X]

  sealed trait FiniteOpen[+X] extends Finite[X], Open[X], Functor[FiniteOpen, X]

  case object NegativeInfinity
    extends Endpoint[Nothing],
            LowerBound[Nothing],
            Infinite,
            EmptyFunctor[NegativeInfinity.type]

  case object PositiveInfinity
    extends Endpoint[Nothing],
            UpperBound[Nothing],
            Infinite,
            EmptyFunctor[PositiveInfinity.type]

  case class After[+X]
    (value: X)
    extends LowerBound[X], FiniteOpen[X], Functor[After, X]:
    override def map[Y](f: X => Y): After[Y] = After(f(value))

  case class Before[+X]
    (value: X)
    extends UpperBound[X], FiniteOpen[X], Functor[Before, X]:
    override def map[Y](f: X => Y): Before[Y] = Before(f(value))

  case class Closed[+X]
    (value: X)
    extends LowerBound[X],
            UpperBound[X],
            Discrete[X],
            Finite[X],
            Functor[Closed, X]:
    override def map[Y](f: X => Y): Closed[Y] = Closed(f(value))
