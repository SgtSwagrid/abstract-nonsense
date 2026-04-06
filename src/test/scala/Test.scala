package io.github.sgtswagrid.nonsense

import io.github.sgtswagrid.nonsense.functor.ops.FunctorOps

sealed trait AorB

case class A(x: Int, y: Int) extends AorB
case class B(name: String)   extends AorB

class TestSeq[+X](val underlying: X*) extends FunctorOps[TestSeq, X]:

  override def map[Y](transform: X => Y): TestSeq[Y] =
    new TestSeq(underlying.map(transform)*)

  override def toString = underlying.mkString("[", ", ", "]")

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

  val x = test.deep.when(_ % 2 == 1).when(x => x >= 10).map(_ + 100)

  println(test2.map(_.deep.when[A].map(_.copy(x = -69))))

  val a = test3.when[A].when(_.x >= 10).mapTo(true).when[B].map(_.name)

  println(a)

  val asdadw = test3.mapToOption[A]
  println(asdadw)

  println(-TestSeq(2.0F, 3.0F, 4.0F).reciprocal)
