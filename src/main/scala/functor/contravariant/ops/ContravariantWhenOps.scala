package io.github.sgtswagrid.nonsense
package functor.contravariant.ops

import io.github.sgtswagrid.nonsense.functor.contravariant.views.{
  ConditionalContravariantView, NegatedTypeContravariantView,
  TypeContravariantView,
}
import io.github.sgtswagrid.nonsense.util.NoContext
import scala.annotation.unchecked.uncheckedVariance
import scala.reflect.ClassTag

/**
  * The [[contrawhen]] operator for
  * [[functor.contravariant.BoundedContravariant]], and its derivatives.
  */
trait ContravariantWhenOps[+Self[-_], +Domain, -X >: Domain]
  extends ContramapOps[Self, Domain, NoContext, X]:

  /**
    * Provides a view of this consumer that only processes inputs satisfying a
    * [[condition]]. Inputs not satisfying the condition are passed through
    * as-is, which requires them to already be of the consumed type [[X]].
    *
    * @param condition
    *   The condition checked on each input after transformation to [[X]].
    *
    * @note
    *   Following any [[contramap]]-like operation, the consumer will be
    *   returned to its original form. The [[condition]] will thereafter be
    *   forgotten.
    *
    * @note
    *   Consecutive calls to [[contrawhen]] will stack conditions, only
    *   processing inputs when they match all of them.
    *
    * @example
    *   ```scala
    *   val consumer: Consumer[Int] = ...
    *   val even = consumer.contrawhen(_ % 2 == 0)
    *   // even only processes even integers, odd ones pass through unchanged.
    *   ```
    */
  def contrawhen(condition: (X @uncheckedVariance) => Boolean)
    : ConditionalContravariantView[Self, Domain, X]

  /**
    * Provides a view of this consumer that only processes inputs of a certain
    * [[Active]] type. Inputs not of that type are passed through as-is, which
    * requires them to already be of the consumed type [[X]].
    *
    * @tparam Active
    *   The subtype of [[X]] whose inputs are to be processed.
    */
  def contrawhen[Active >: Domain : ClassTag]
    : TypeContravariantView[Self, Domain, X, Active]

  /**
    * Provides a view of this consumer that skips inputs of a certain
    * [[Inactive]] type, passing them through unchanged.
    *
    * @tparam Inactive
    *   The subtype of [[X]] whose inputs are left unprocessed.
    */
  def contraunless[Inactive >: Domain : ClassTag]
    : NegatedTypeContravariantView[Self, Domain, X, Inactive]

  /**
    * A version of [[contrawhen]] where the input to [[condition]] is provided
    * implicitly as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  final inline def contrawhenCtx
    (condition: (X @uncheckedVariance) ?=> Boolean)
    : ConditionalContravariantView[Self, Domain, X] =
    contrawhen(value => condition(using value))

  /**
    * Alias for `this.contrawhen(_ == value)`.
    *
    * @see
    *   [[contrawhen]]
    */
  final inline def contrawhenEquals
    (value: X @uncheckedVariance)
    : ConditionalContravariantView[Self, Domain, X] = contrawhen(_ == value)
