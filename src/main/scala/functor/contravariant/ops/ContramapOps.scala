package io.github.sgtswagrid.nonsense
package functor.contravariant.ops

import io.github.sgtswagrid.nonsense.caching.Cache

/** The [[contramap]] operator for [[BoundedContravariant]], and its derivatives. */
trait ContramapOps[
  +Self[-_],
  +Domain,
  -Context[_],
  -X >: Domain,
]:

  /**
    * ## `contramap` (from [[Contravariant]])
    *
    * Transforms the input to this consumer in advance.
    *
    * @param transform
    *   An arbitrary mapping applied to each input value.
    *
    * @tparam Y
    *   The new input type to replace [[X]].
    *
    * @return
    *   A projected version of this consumer, leaving this original unchanged.
    *
    * @example
    *   ```scala
    *   trait Eater[-X](???) extends Contravariant[Eater, X]:
    *
    *     def eat(food: X): Eater[X] =
    *       println(s"Yummy $food!")
    *       ???
    *
    *     override def contramap[Y : NoContext](f: Y => X): Eater[Y] = ???
    *
    *   val carnivore: Eater[Meat] = ???
    *
    *   type Meal = List[Food]
    *
    *   val omnivore: Eater[Meal] =
    *     carnivore.contramap(_.filter(_.isInstanceOf[Meat]))
    *
    *   omnivore.eat(Seq(Steak, Salad)) // "Yummy List(steak)!"
    *   ```
    */
  def contramap[Y >: Domain : Context](transform: Y => X): Self[Y]

  /**
    * A version of [[contramap]] where the input to [[transform]] is provided
    * implicitly as
    * [context](https://docs.scala-lang.org/scala3/reference/contextual/context-functions.html).
    */
  final inline def contramapWithContext[Y >: Domain : Context]
    (transform: Y ?=> X)
    : Self[Y] = contramap(value => transform(using value))

  /**
    * A version of [[contramap]] where [[transform]] is cached so as to prevent
    * repeated computation for the same input.
    *
    * @note
    *   All outputs of [[transform]] will be kept in memory until the returned
    *   structure is garbage-collected.
    */
  final inline def contramapWithCache[Y >: Domain : Context]
    (transform: Y => X)
    : Self[Y] = contramap(Cache.memoise(transform))

