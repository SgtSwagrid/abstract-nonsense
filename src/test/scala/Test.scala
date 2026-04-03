package io.github.sgtswagrid.nonsense

import io.github.sgtswagrid.nonsense.functor.{BoundedFunctorOps, FunctorOps}

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

  val test2 = TestSeq(
    TestSeq(TestSeq(1, 2), TestSeq(3, 4)),
    TestSeq(TestSeq(5, 6), TestSeq(7, 8)),
  )

  val x = test.deep.when(_ % 2 == 1).map(_ + 100)

  println(test.deep.when(_ % 2 == 1).map(_ + 100))
