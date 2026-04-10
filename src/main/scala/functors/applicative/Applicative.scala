package io.github.sgtswagrid.nonsense
package functors.applicative

import io.github.sgtswagrid.nonsense.functors.applicative.ops.{
  NumericApplicativeOps, ZipOps,
}
import io.github.sgtswagrid.nonsense.functors.covariant.Functor

trait Applicative[Self[+X] <: Applicative[Self, X], +Output]
  extends Functor[Self, Output], ZipOps[Self, Output]
//NumericApplicativeOps[Self, Output]
