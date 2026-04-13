package io.github.sgtswagrid.nonsense
package interval

import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedRing
import io.github.sgtswagrid.nonsense.algebra.ordered.OrderedRing.{*, given}
import io.github.sgtswagrid.nonsense.functors.covariant.ContextFunctor
import io.github.sgtswagrid.nonsense.interval.Endpoint.*
import io.github.sgtswagrid.nonsense.misc.Conjunction.&&
import io.github.sgtswagrid.nonsense.misc.OrderOps
import io.github.sgtswagrid.nonsense.shape.BoundedOps
import io.github.sgtswagrid.nonsense.transform.{NegateOps, ScaleOps}
import scala.annotation.unchecked.uncheckedVariance
import scala.reflect.ClassTag

sealed trait Endpoint[+X]
  extends ContextFunctor[Endpoint, OrderedRing && ClassTag, X],
          NegateOps[Endpoint[X]],
          ScaleOps[Endpoint, X],
          OrderOps.Wrapped[Endpoint, X]:

  def valueOption: Option[X]

  protected def self: Endpoint[X] = this

  override inline def compare[Y >: X : Ordering](that: Endpoint[Y]): Int =
    (this, that) match
      case (a, b) if a == b                              => 0
      case (Finite(a), Finite(b)) if a != b              => a.compare(b)
      case (NegativeInfinity, _) | (_, PositiveInfinity) => -1
      case (_, NegativeInfinity) | (PositiveInfinity, _) => 1
      case (Before(_), _) | (_, After(_))                => -1
      case (_, Before(_)) | (After(_), _)                => 1
      case _ => throw IllegalStateException()

object Endpoint:

  sealed trait LowerBound[+X] extends Endpoint[X], NegateOps[UpperBound[X]]

  object LowerBound:
    def unapply[X](x: Endpoint[X]): Boolean = x.isInstanceOf[LowerBound[?]]

  sealed trait UpperBound[+X] extends Endpoint[X], NegateOps[LowerBound[X]]

  object UpperBound:
    def unapply[X](x: Endpoint[X]): Boolean = x.isInstanceOf[UpperBound[?]]

  sealed trait Discrete[+X]
    extends Endpoint[X],
            ContextFunctor[Discrete, OrderedRing && ClassTag, X],
            NegateOps[Discrete[X]],
            ScaleOps[Discrete, X]

  object Discrete:
    def unapply[X](x: Endpoint[X]): Boolean = x.isInstanceOf[Discrete[?]]

  sealed trait Finite[+X]
    extends Endpoint[X],
            ContextFunctor[Finite, OrderedRing && ClassTag, X],
            NegateOps[Finite[X]],
            ScaleOps[Finite, X]:

    def value: X

    override inline def valueOption: Some[X] = Some(value)

  object Finite:
    def unapply[X](x: Endpoint[X]): Option[X] = x.valueOption

  sealed trait Infinite
    extends Discrete[Nothing],
            ContextFunctor[
              [_] =>> Infinite,
              OrderedRing && ClassTag,
              Nothing,
            ],
            BoundedOps.Universal,
            NegateOps[Infinite],
            ScaleOps[[_] =>> Infinite, Nothing]:

    override inline def valueOption: None.type = None

    override inline def scale[Y : {OrderedRing, ClassTag}]
      (factor: Y)
      : Infinite = if factor >= zero then this else negate

  object Infinite:
    def unapply[X](x: Endpoint[X]): Boolean = x.isInstanceOf[Infinite]

  sealed trait Open[+X : {OrderedRing, ClassTag}]
    extends Endpoint[X],
            ContextFunctor[Open, OrderedRing && ClassTag, X],
            BoundedOps.Covariant[X],
            BoundedOps.NonEmpty,
            NegateOps[Open[X]],
            ScaleOps[Open, X]

  object Open:
    def unapply[X](x: Endpoint[X]): Boolean = x.isInstanceOf[Open[?]]

  sealed trait FiniteOpen[+X]
    extends Finite[X],
            Open[X],
            ContextFunctor[FiniteOpen, OrderedRing && ClassTag, X],
            NegateOps[FiniteOpen[X]],
            ScaleOps[FiniteOpen, X]:

    override inline def scale[Y >: X : {OrderedRing, ClassTag}]
      (factor: Y)
      : FiniteOpen[Y] =
      if factor >= zero then map(factor * _) else map(-factor * _).negate

  object FiniteOpen:

    def unapply[X](x: Endpoint[X]): Option[X] = x match
      case Before(x) => Some(x)
      case After(x)  => Some(x)
      case _         => None

  case object NegativeInfinity
    extends Endpoint[Nothing],
            LowerBound[Nothing],
            Infinite,
            ContextFunctor[
              [_] =>> NegativeInfinity.type,
              OrderedRing && ClassTag,
              Nothing,
            ],
            NegateOps[PositiveInfinity.type]:

    override inline def map[Y : OrderedRing && ClassTag]
      (f: Nothing => Y)
      : NegativeInfinity.type = this

    override inline def negate: PositiveInfinity.type = PositiveInfinity

  case object PositiveInfinity
    extends Endpoint[Nothing],
            UpperBound[Nothing],
            Infinite,
            ContextFunctor[
              [_] =>> PositiveInfinity.type,
              OrderedRing && ClassTag,
              Nothing,
            ],
            NegateOps[NegativeInfinity.type]:

    override inline def map[Y : OrderedRing && ClassTag]
      (f: Nothing => Y)
      : PositiveInfinity.type = this

    override inline def negate: NegativeInfinity.type = NegativeInfinity

  case class After[+X : {OrderedRing, ClassTag}]
    (value: X)
    extends LowerBound[X],
            FiniteOpen[X],
            ContextFunctor[After, OrderedRing && ClassTag, X],
            NegateOps[Before[X]]:

    override inline def map[Y : OrderedRing && ClassTag](f: X => Y): After[Y] =
      After(f(value))

    override inline def negate: Before[X] = Before(-value)

    override protected inline def inBoundsTyped
      (x: X @uncheckedVariance)
      : Boolean = x > value

  case class Before[+X : {OrderedRing, ClassTag}]
    (value: X)
    extends UpperBound[X],
            FiniteOpen[X],
            ContextFunctor[Before, OrderedRing && ClassTag, X],
            NegateOps[After[X]]:

    override inline def map[Y : OrderedRing && ClassTag](f: X => Y): Before[Y] =
      Before(f(value))

    override inline def negate: After[X] = After(-value)

    override protected inline def inBoundsTyped
      (x: X @uncheckedVariance)
      : Boolean = x < value

  case class Closed[+X : OrderedRing]
    (value: X)
    extends LowerBound[X],
            UpperBound[X],
            Discrete[X],
            Finite[X],
            ContextFunctor[Closed, OrderedRing && ClassTag, X],
            NegateOps[Closed[X]],
            ScaleOps[Closed, X]:

    override inline def map[Y : OrderedRing && ClassTag](f: X => Y): Closed[Y] =
      Closed(f(value))

    override inline def negate: Closed[X] = Closed(-value)

    override inline def scale[Y >: X : {OrderedRing, ClassTag}]
      (factor: Y)
      : Closed[Y] = map(_ * factor)

  given [X : OrderedRing && ClassTag as X]: OrderedRing[X] = X.left
  given [X : OrderedRing && ClassTag as X]: ClassTag[X]    = X.right
