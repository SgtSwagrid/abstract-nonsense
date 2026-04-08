package io.github.sgtswagrid.nonsense
package functor.bivariant

/**
  * A profunctor is something that can be both mapped and contra-mapped over.
  *
  * Any implementation need only define [[map]] and [[contraMap]], and
  * everything else will be derived from those.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Transformation`).
  *
  * @tparam Input
  *   The type of value that can be handled by this structure (e.g. `Event`).
  *
  * @tparam Output
  *   The type of value that this structure can produce (e.g. `Action`).
  */
trait Profunctor[
  +Self[-I, +O] <: Profunctor[Self, I, O],
  -Input,
  +Output,
] extends BoundedProfunctor[Self, Nothing, Any, Input, Output]
