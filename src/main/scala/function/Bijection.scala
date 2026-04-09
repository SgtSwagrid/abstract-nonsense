package io.github.sgtswagrid.nonsense
package function

import io.github.sgtswagrid.nonsense.function.Bijection.<=>

/**
  * A bijection is an invertible function. A function `f` is invertible if and
  * only if it is both:
  *
  *   - Injective: `∀ i₁, i₂ ∈ In . i₁ ≠ i₂ ⇒ f(i₁) ≠ f(i₂)`
  *   - Surjective: `∀ o ∈ Out ∃ i ∈ In . f(i) = o`
  *
  * Can be used as an ordinary function `A => B`, or otherwise call [[inverse]]
  * to obtain the inverse bijection.
  *
  * @tparam In
  *   The input to the function.
  *
  * @tparam Out
  *   The output of the function.
  */
trait Bijection[In, Out] extends (In => Out):

  /**
    * Provides the inverse of this bijective function.
    *
    * @note
    *   This operator is an involution, meaning that applying is twice will
    *   return the original value: `this.invert.invert == this`.
    *
    * @note
    *   The existence of this operator suffices to prove the existence of an
    *   inverse. Safety is assured.
    *
    * @note
    *   Whether the inverse is computed by this operator, or is simply known in
    *   advance and returned as-is, depends on the underlying type.
    */
  def inverse: Out <=> In

  /**
    * Composes two instances of [[Bijection]] into a new [[Bijection]], with
    * this one applied last.
    *
    * @note
    *   Specialises [[Function.compose]] for the case where both operands are
    *   invertible, in which case the composition is also invertible.
    */
  inline infix def compose[Pre](that: Pre <=> In): Bijection[Pre, Out] =
    Bijection.Explicit(
      x => this(that(x)),
      x => that.inverse(this.inverse(x)),
    )

  /**
    * Composes two instances of [[Bijection]] into a new [[Bijection]], with
    * this one applied first.
    *
    * @note
    *   Specialises [[Function.andThen]] for the case where both operands are
    *   invertible, in which case the composition is also invertible.
    */
  inline infix def andThen[Post](that: Out <=> Post): Bijection[In, Post] =
    Bijection.Explicit(
      x => that(this(x)),
      x => this.inverse(that.inverse(x)),
    )

object Bijection:

  /** Alias for [[Bijection]]. */
  infix type <=>[In, Out] = Bijection[In, Out]

  /**
    * A [[Bijection]] constructed by separately and explicitly providing the
    * [[forward]] and [[backward]] versions of a function.
    *
    * @param forward
    *   The forward direction of the represented function.
    *
    * @param backward
    *   The backward direction of the represented function.
    *
    * @tparam In
    *   The input to the function.
    *
    * @tparam Out
    *   The output of the function.
    *
    * @note
    *   The caller is responsible for ensuring that [[forward]] and [[backward]]
    *   are indeed each other's inverse. No checks are made, and this property
    *   is henceforth assumed to hold.
    */
  class Explicit[In, Out]
    (forward: In => Out, backward: Out => In)
    extends (In <=> Out):

    override final inline def apply(value: In): Out = forward(value)

    override final inline def inverse: Out <=> In = Explicit(backward, forward)
