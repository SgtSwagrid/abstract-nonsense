## io.github.sgtswagrid.nonsense.algebra.ops

This package contains _operators_ for members of algebraic types.
There is exactly one operators trait for each base trait in the parent directory.
For example, `AdditiveSemigroupOps` contains, among other things,
the `+` extension method for types with an available `AdditiveSemigroup`.

### Intended usage

The end user should not directly reference this package.
Instead, he/she should refer to the relevant companion object.
Everything contained here is available there.

For any structure, e.g. `Ring`, use the following import statement
to receive all necessary syntax for working with that structure:
```scala 3
import io.github.sgtswagrid.nonsense.algebra.<Structure>.{*, given}
```

### Inheritance structure

#### Companion objects

Each trait herein is extended by the companion object of the corresponding typeclass.
The reason for having a separate hierarchy here is that companion objects,
by virtue of being objects, cannot form subtyping relations among themselves.

#### Internal hierarchy

The inheritance hierarchy between the operator traits
is in the same direction to that of the corresponding base traits.
That is, for example, _OrderedFieldOps_ extends _OrderedSemifieldOps_
if and only if _OrderedField_ extends _OrderedSemifield_.

The reason is that operators for more general things
can also be used for more specific things
(i.e. operators are covariant in the type on which they act).
This way, we avoid duplication.
