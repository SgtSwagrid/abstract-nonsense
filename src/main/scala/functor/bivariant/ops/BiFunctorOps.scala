package io.github.sgtswagrid.nonsense
package functor.bivariant.ops

/**
  * A bivariant functor is something that can be both mapped and contra-mapped
  * over.
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
trait BiFunctorOps[+Self[-_, +_], -Input, +Output]
  extends BoundedBiFunctorOps[Self, Input, Output, Nothing, Any]
