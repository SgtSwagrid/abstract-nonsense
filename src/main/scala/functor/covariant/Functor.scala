package io.github.sgtswagrid.nonsense
package functor.covariant

import io.github.sgtswagrid.nonsense.functor.covariant.ops.DeepOps
import io.github.sgtswagrid.nonsense.functor.covariant.views.DeepFunctorView

/**
  * A functor is something that can be mapped over.
  *
  * Any implementation need only define [[map]], and everything else will be
  * derived from it.
  *
  * Take care to ensure that the following functor laws hold:
  *
  *   1. Identity: `fa.map(identity) == fa`
  *   2. Composition: `fa.map(f).map(g) == fa.map(f.andThen(g))`
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[List]]).
  *
  * @tparam Output
  *   The type of value contained in this structure (e.g. [[Int]]).
  */
trait Functor[+Self[+_], +Output]
  extends BoundedFunctor[Self, Any, Output],
          ContextFunctor[Self, [_] =>> DummyImplicit, Output],
          DeepOps[Self, Output]:

  override final inline def deep[Inner[+_], InnerOutput]
    (using Output <:< Functor[Inner, InnerOutput])
    : DeepFunctorView[Self, Inner, InnerOutput] =
    DeepFunctorView(asInstanceOf[Functor[Self, Functor[Inner, InnerOutput]]])
