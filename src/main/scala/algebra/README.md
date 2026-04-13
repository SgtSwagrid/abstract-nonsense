## io.github.sgtswagrid.nonsense.algebra

This package contains type classes for algebraic structures.

### Purpose

These structures allow for abstraction over "rules of composition".
That is, it becomes possible to define an operator not for just for a specific type,
but rather for _all_ types where it can be shown that certain properties are obeyed.
Each of the large number of traits here simply correspond to
different combinations of operators that are available or required.

### Intended usage

For any structure, e.g. `Ring`, use the following import statement
to receive all necessary syntax for working with that structure:
```scala 3
import io.github.sgtswagrid.nonsense.algebra.<Structure>.{*, given}
```

Thereafter, you can freely use `Ring` as a context bound on type parameters,
and enjoy the associated syntax:
```scala 3
def f[X : Ring](x: X, y: X, z: X): X = (x - y) * z + x
```

Users of your library will be able to use `f` on any type for which a `Ring` is available.
(From the Scala standard library, this includes `Float` and `Double`.)
For in-built types, your users won't even need to add any additional import statements,
as the Scala compiler will find the evidence automatically.

### See also

Of the three subpackages, only one ([ordered](ordered)) may be relevant for users of this library.
It specialises each of the type classes here with `Ordering`.

The remaining subpackages ([builder](builder) and [ops](ops)) are only for internal use.
They contain operators for producing instances of type classes, and extension methods for values _with_ type classes, respectively.
All the contents from the above should instead be obtained through the companion object of the respective type class.
If you need to work with these subpackages, consult the corresponding README files,
especially as the inheritance hierarchy can be counterintuitive upon first sight.
