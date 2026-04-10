package io.github.sgtswagrid.nonsense
package groups

import io.github.sgtswagrid.nonsense.groups.builder.FieldBuilder
import io.github.sgtswagrid.nonsense.groups.ops.FieldOps

trait Field[X] extends EuclideanRing[X], MultiplicativeGroup[X]

object Field extends FieldBuilder, FieldOps
