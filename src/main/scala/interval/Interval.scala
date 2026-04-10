package io.github.sgtswagrid.nonsense
package interval

import io.github.sgtswagrid.nonsense.functors.bifunctor.ContextBifunctor
import io.github.sgtswagrid.nonsense.groups.ordered.OrderedRing.{*, given}
import scala.reflect.ClassTag

sealed trait Interval[+Start, +End]
  extends ContextBifunctor.Symmetric[Interval, Ordering, Start, End]

object Interval:

  case class NonEmpty[Start, End]
    (start: Start, end: End)
    (using Ordered[Start | End])
    extends Interval[Start, End],
            ContextBifunctor.Symmetric[NonEmpty, Ordering, Start, End]:

    override def bimap[L : Ordered, R : Ordered]
      (transformStart: Start => L)
      (transformEnd: End => R)
      : NonEmpty[L, R] = NonEmpty(
      transformStart(start),
      transformEnd(end),
    )

  case object Empty
    extends Interval[Nothing, Nothing],
            ContextBifunctor.Symmetric[
              [_, _] =>> Empty.type,
              Ordered,
              Nothing,
              Nothing,
            ]:

    override def bimap[L : Ordering, R : Ordering]
      (transformStart: Nothing => L)
      (transformEnd: Nothing => R)
      : Empty.type = this

  type Uniform[X] = Interval[X, X]

  type Rich[+X] = Interval[
    Endpoint.LowerBound[X],
    Endpoint.UpperBound[X],
  ]

  type Finite[+X] = Interval[
    Endpoint.LowerBound[X] & Endpoint.Finite[X],
    Endpoint.UpperBound[X] & Endpoint.Finite[X],
  ]

  type Discrete[+X] = Interval[
    Endpoint.LowerBound[X] & Endpoint.Discrete[X],
    Endpoint.UpperBound[X] & Endpoint.Discrete[X],
  ]

  def from[X : {OrderedRing, ClassTag}](start: X): Interval.Discrete[X] =
    given Ordering[Endpoint[X]] = ???
    Interval.NonEmpty(using summon[Ordering[Endpoint[X]]])(
      Endpoint.Closed(start),
      Endpoint.PositiveInfinity,
    )

  def to[X : {OrderedRing, ClassTag}](end: X): Interval.Discrete[X] =
    given Ordering[Endpoint[X]] = ???
    Interval.NonEmpty(using summon[Ordering[Endpoint[X]]])(
      Endpoint.NegativeInfinity,
      Endpoint.Closed(end),
    )
