package io.github.sgtswagrid.nonsense
package transform

trait ScaleOps[+Self[_], +X]:

  /** Uniformly scale this structure by a constant factor. */
  def scale[Y >: X : Numeric](factor: Y): Self[Y]

  /** Alias for [[scale]]. */
  final inline infix def * [Y >: X : Numeric](factor: Y): Self[Y] =
    scale(factor)
