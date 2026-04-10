package io.github.sgtswagrid.nonsense
package shape

import scala.annotation.unchecked.uncheckedVariance
import scala.reflect.ClassTag

/**
  * A structure which exists over some bounded subset of [[X]].
  *
  * @tparam X
  *   The value type in which bounds checks are defined.
  *
  * @see
  *   [[BoundedOps.Static]], [[BoundedOps.Covariant]]
  */
trait BoundedOps[-X]:

  /** Determine whether [[value]] lies within the bounds of this structure. */
  def inBounds(value: X): Boolean

  /** Determine whether [[value]] lies outside the bounds of this structure. */
  def outOfBounds(value: X): Boolean = !inBounds(value)

  /**
    * Determine whether this structure is empty, i.e. has no value for which
    * [[inBounds]] is `true`.
    */
  def isEmpty: Boolean

  /**
    * Determine whether this structure is non-empty, i.e. has some value for
    * which [[inBounds]] is `true`.
    */
  def nonEmpty: Boolean = !isEmpty

object BoundedOps:

  /** A version of [[BoundedOps]] where membership is known at compile-time. */
  trait Static[-X] extends BoundedOps[X]:

    type IsEmpty

    type InBounds[_ <: X]

  /**
    * A version of [[BoundedOps]] where the type parameter [[X]] is
    * [covariant](https://docs.scala-lang.org/tour/variances.html#covariance)
    * rather than
    * [contravariant](https://docs.scala-lang.org/tour/variances.html#contravariance).
    * This means that [[inBounds]] accepts [[Any]] instead of just [[X]], and
    * defers internally to a new method [[inBoundsTyped]].
    */
  trait Covariant[+X : ClassTag] extends BoundedOps[Any]:

    override final inline def inBounds(value: Any): Boolean = inline value match
      case value: X => inBoundsTyped(value)
      case _        => false

    /**
      * Determine whether [[value]] lies within the bounds of this structure.
      * Used internally by [[inBounds]] after the value's type has been checked
      * for compliance.
      */
    protected def inBoundsTyped(value: X @uncheckedVariance): Boolean

  trait Empty extends Static[Any]:

    override type IsEmpty     = true
    override type InBounds[_] = false

    override def isEmpty: true               = true
    override def inBounds(value: Any): false = false

  trait NonEmpty extends BoundedOps[Nothing]:

    override def isEmpty: Boolean = false

  trait Full[-X] extends BoundedOps[X], NonEmpty:

    override def inBounds(value: X): true = true

  trait Universal extends Full[Any]

  trait CovariantFull[+X] extends Covariant[X], NonEmpty:

    override protected inline def inBoundsTyped
      (value: X @uncheckedVariance)
      : true = true
