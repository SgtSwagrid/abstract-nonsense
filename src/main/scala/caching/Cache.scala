package io.github.sgtswagrid.nonsense
package caching

import scala.collection.mutable

object Cache:

  def memoise[X, Y](f: X => Y): X => Y =
    val cache = mutable.Map.empty[X, Y]
    x => cache.getOrElseUpdate(x, f(x))
