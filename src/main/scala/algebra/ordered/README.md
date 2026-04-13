## io.github.sgtswagrid.nonsense.algebra.ordered

This package contains _ordered_ variants of the algebraic type classes from the parent directory.
There is exactly one _ordered_ trait for each _unordered_ trait.
For example, `OrderedSemigroup` is for a `Semigroup` that also has an `Ordering`.

### Inheritance structure

The inheritance hierarchy between the _ordered_ traits is an exact replica of
that between the _unordered_ variants.
This applies to the type classes themselves, in addition to
the traits from [ops](ops) and [builder](builder).

Additionally, there are crossing links between the corresponding
_ordered_ and _unordered_ version of each trait:
* For the type classes and [ops](ops), the _ordered_ version extends the _unordered_ one.
* For [builder](builder), the reverse applies, i.e. the _unordered_ version extends the _ordered_ one.
  This contravariance is explained [here](../builder/README.md).

### Design goals

Acknowledging that the provision of this package clearly violates
[orthogonality](https://en.wikipedia.org/wiki/Orthogonality_(programming)),
it is contended that the combination of _combine_- and _compare_-style capability
is ubiquitous enough to warrant separate attention.

The following benefits are hereby achieved:

#### 1. Provide operators that simultaneously require both capabilities

This includes, for example:
* `abs`
* `sign`
* `isPositive`
* `isNegative`
* `isNonNegative`
* `isNonPositive`

#### 2. Support such a combination with a _single_ type class

`[X: OrderedField]` is (slightly) easier to type than `[X: {Field, Ordering}]`,
but this isn't the only benefit.
In certain contexts, it can be the case that it isn't possible to specify multiple context bounds.
For instance, this can apply if the context bound is generic.
