package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.MultiplicativeIdentity
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedMultiplicativeIdentityBuilder

/** Methods for constructing [[MultiplicativeIdentity]] type classes. */
trait MultiplicativeIdentityBuilder
  extends OrderedMultiplicativeIdentityBuilder, MultiplicativeMonoidBuilder
