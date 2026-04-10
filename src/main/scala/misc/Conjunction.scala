package io.github.sgtswagrid.nonsense
package misc

import io.github.sgtswagrid.nonsense.misc.Conjunction.{
  LeftConjunction, RightConjunction,
}

class Conjunction[+F[_], +G[_], X : {F, G}]
  extends LeftConjunction[F, X], RightConjunction[G, X]:

  def use[Y](f: (F[X], G[X]) ?=> Y): Y = f(using left, right)

object Conjunction:

  trait LeftConjunction[+F[_], X : F]:
    def left: F[X] = summon[F[X]]

  trait RightConjunction[+G[_], X : G]:
    def right: G[X] = summon[G[X]]

  type &&[F[_], G[_]] = [X] =>> Conjunction[F, G, X]

  given [F[_], G[_], X : {F, G}]: Conjunction[F, G, X] = Conjunction[F, G, X]

  given [F[_], X](using C: LeftConjunction[F, X]): F[X]  = C.left
  given [G[_], X](using C: RightConjunction[G, X]): G[X] = C.right
