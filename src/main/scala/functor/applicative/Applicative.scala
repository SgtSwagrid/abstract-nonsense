package io.github.sgtswagrid.nonsense
package functor.applicative

import io.github.sgtswagrid.nonsense.functor.applicative.ops.{
  NumericApplicativeOps, ZipOps,
}
import io.github.sgtswagrid.nonsense.functor.covariant.Functor

trait Applicative[Self[+X] <: Applicative[Self, X], +Output]
  extends Functor[Self, Output], ZipOps[Self, Output]
//NumericApplicativeOps[Self, Output]
