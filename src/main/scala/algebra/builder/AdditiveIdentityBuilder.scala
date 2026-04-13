package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.AdditiveIdentity
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedAdditiveIdentityBuilder

/** Methods for constructing [[AdditiveIdentity]] type classes. */
trait AdditiveIdentityBuilder
  extends OrderedAdditiveIdentityBuilder, AdditiveMonoidBuilder
