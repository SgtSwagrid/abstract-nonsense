package io.github.sgtswagrid.nonsense
package util

/** A context bound which indicates that no context is required. */
type NoContext[_] = DummyImplicit

/** Evidence that any type is a subtype of [[Any]]. */
given anyTop[T]: (T <:< Any) = summon[T =:= T]
