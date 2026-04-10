package io.github.sgtswagrid.nonsense
package interval

import io.github.sgtswagrid.nonsense.functors.bifunctor.Bifunctor

sealed trait Interval[+Start, +End] extends Bifunctor[Interval, Start, End]

object Interval:

  case class NonEmpty[+Start, +End]
    (start: Start, end: End)
    extends Interval[Start, End], Bifunctor[NonEmpty, Start, End]:

    override inline def bimap[L, R]
      (transformStart: Start => L)
      (transformEnd: End => R)
      : NonEmpty[L, R] = NonEmpty(
      transformStart(start),
      transformEnd(end),
    )

  case object Empty
    extends Interval[Nothing, Nothing],
            Bifunctor[[_, _] =>> Empty.type, Nothing, Nothing]:

    override inline def bimap[L, R]
      (transformStart: Nothing => L)
      (transformEnd: Nothing => R)
      : Empty.type = this
