package io.github.sgtswagrid.nonsense
package algebra.builder

import io.github.sgtswagrid.nonsense.algebra.AdditiveGroup
import io.github.sgtswagrid.nonsense.algebra.ordered.builder.OrderedAdditiveGroupBuilder

/** Methods for constructing [[AdditiveGroup]] type classes. */
trait AdditiveGroupBuilder extends OrderedAdditiveGroupBuilder, RingBuilder
