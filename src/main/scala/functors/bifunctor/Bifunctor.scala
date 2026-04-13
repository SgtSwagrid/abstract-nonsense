package io.github.sgtswagrid.nonsense
package functors.bifunctor

/**
  * A bifunctor is something that can be mapped over in two different ways.
  *
  * Any implementation need only define [[bimap]], and everything else will be
  * derived from it.
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. [[Either]]).
  *
  * @tparam Left
  *   The type of value contained on the left-hand side of this structure (e.g.
  *   [[Int]]).
  *
  * @tparam Right
  *   The type of value contained on the right-hand side of this structure (e.g.
  *   [[Int]]).
  *
  * @see
  *   [[io.github.sgtswagrid.nonsense.functor.bivariant.Profunctor]] for a
  *   variant that is contravariant in one of the type parameters.
  *
  * Any implementation need only define [[bimapImpl]], and everything else will
  * be derived from it.
  */
trait Bifunctor[+Self[+_, +_], +Left, +Right]
  extends LeftBoundedBifunctor[Self, Any, Left, Right],
          RightBoundedBifunctor[Self, Any, Left, Right],
          ContextBifunctor[
            Self,
            [_] =>> DummyImplicit,
            [_] =>> DummyImplicit,
            Left,
            Right,
          ]

object Bifunctor:

  /**
    * A [[Bifunctor]] that contains no values.
    *
    * @tparam Self
    *   The singleton produced by all [[Bifunctor.bimap]]-like operations.
    */
  trait Empty[+Self : ValueOf]
    extends Bifunctor[[_, _] =>> Self, Nothing, Nothing]:

    override protected def bimapImpl[LeftPost, RightPost]
      (transformLeft: Nothing => LeftPost)
      (transformRight: Nothing => RightPost)
      : Self = valueOf[Self]
