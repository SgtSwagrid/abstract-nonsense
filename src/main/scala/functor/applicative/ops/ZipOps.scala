package io.github.sgtswagrid.nonsense
package functor.applicative.ops

import io.github.sgtswagrid.nonsense.functor.applicative.Applicative

trait ZipOps[Self[+X] <: Applicative[Self, X], +Output]:

  infix def zip[Right](that: Self[Right]): Self[(Output, Right)]

  final inline def zipWith[Right, Result]
    (that: Self[Right])
    (combine: (Output, Right) => Result)
    : Self[Result] = zip(that).map(combine.tupled)

  final inline def zipLeft(that: Self[Any]): Self[Output] = zip(that).map(_(0))

  final inline def zipRight[Right](that: Self[Right]): Self[Right] = zip(that)
    .map(_(1))
