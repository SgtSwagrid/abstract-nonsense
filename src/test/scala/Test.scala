package io.github.sgtswagrid.nonsense

import io.github.sgtswagrid.nonsense.functor.bifunctor.Bifunctor
import io.github.sgtswagrid.nonsense.functor.covariant.Functor

sealed trait AorB

case class A(x: Int, y: Int) extends AorB
case class B(name: String)   extends AorB

class TestSeq[+X](val underlying: X*) extends Functor[TestSeq, X]:

  override protected def mapImpl[Y](transform: X => Y): TestSeq[Y] =
    new TestSeq(underlying.map(transform)*)

  override def toString = underlying.mkString("[", ", ", "]")

case class AB[+A, +B](first: A, second: B) extends Bifunctor[AB, A, B]:

  override def bimap[PostLeft, PostRight]
    (transformLeft: A => PostLeft)
    (transformRight: B => PostRight)
    : AB[PostLeft, PostRight] = AB(
    transformLeft(first),
    transformRight(second),
  )

object Test extends App:

  val test = new TestSeq(
    TestSeq(4, 5, 6),
    TestSeq(0, 1, 2),
    TestSeq(10, 11, 12),
  )

  val test2 = TestSeq[TestSeq[TestSeq[AorB]]](
    TestSeq(
      TestSeq(A(0, 0), A(1, 2)),
      TestSeq(A(1, 2), B("Hello, World!")),
    ),
    TestSeq(
      TestSeq(B("Weird"), A(6, 7)),
      TestSeq(A(-1, -1), A(100, 200)),
    ),
  )

  val test3 = TestSeq(A(0, 1), B("Hello, World!"), A(10, 100))

  val test4 = AB(TestSeq(1, 2, 3), "Hello")

  println(test4.left.deep.map(_ + 1))

  println(test2.deep.deep.when[A].mapToLeft.deep.deep.when[B].mapToRight)

  val x = test.deep.when(_ % 2 == 1).when(x => x >= 10).map(_ + 100)

  println(test2.map(_.deep.when[A].map(_.copy(x = -69))))

  println(test2.deep.deep.mapTo(6))

  val a = test3.when[A].when(_.x >= 10).mapToTrue.when[B].map(_.name)

  println(a)

  println(-TestSeq(2.0F, 3.0F, 4.0F) * 19.0F)

  println(TestSeq(1, 6, -2, 0, 43).support.invert)

  println(TestSeq((1, "A"), (2, "B"), (3, "C")).unzip)

  println(AB(5, "Hello").left.map(_ + 12))
