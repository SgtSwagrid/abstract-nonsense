package io.github.sgtswagrid.nonsense
package functor.contravariant

/**
  * ## Bounded Contravariant Functor
  *
  * A bounded contravariant functor is a restricted [[Contravariant]] that can
  * only consume values with certain properties.
  *
  * ### Constraints
  *
  *   1. A lower bound [[Domain]] on the type [[X]].
  *
  * ### Signature
  *
  * @tparam Self
  *   The kind of structure that this is (e.g. `Consumer`).
  *
  * @tparam Domain
  *   The lower bound on [[X]] (e.g. [[Nothing]]).
  *
  * @tparam X
  *   The type of value consumed (e.g. `Event`).
  */
trait BoundedContravariant[+Self[-_], +Domain, -X]:

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
    *     override def contramap[Y](f: Y => X): Eater[Y] = ???
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
  def contramap[Y >: Domain](transform: Y => X): Self[Y]
