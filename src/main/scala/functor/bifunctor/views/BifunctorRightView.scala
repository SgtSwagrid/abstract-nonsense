package io.github.sgtswagrid.nonsense
package functor.bifunctor.views

import io.github.sgtswagrid.nonsense.functor.bifunctor.BoundedBifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.BoundedFunctor

/**
  * A view of a bifunctor that only maps values on the right. Obtained by
  * calling [[BoundedBifunctor.right]].
  *
  * @param base
  *   The underlying structure.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam LeftCodomain
  *   The upper bound on [[Left]] following any [[map]]-like operation.
  *
  * @tparam RightCodomain
  *   The upper bound on [[Right]] following any [[map]]-like operation.
  *
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure (e.g.
  *   [[Int]]).
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure (e.g.
  *   [[Int]]).
  */
class BifunctorRightView[
  +Self[+_, +_],
  -LeftCodomain,
  -RightCodomain,
  +Left <: LeftCodomain,
  +Right <: RightCodomain,
]
  (
    base: BoundedBifunctor[
      Self,
      LeftCodomain,
      RightCodomain,
      Left,
      Right,
    ],
  )
  extends BoundedFunctor[
    [X] =>> Self[Left, X],
    RightCodomain,
    Right,
  ]:

  override inline def map[RightPost <: RightCodomain]
    (transform: Right => RightPost)
    : Self[Left, RightPost] = base.bimap(identity)(transform)
