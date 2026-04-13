## io.github.sgtswagrid.nonsense.algebra.builder

This package contains _builders_ that provide
[givens](https://docs.scala-lang.org/scala3/reference/contextual/givens.html)
for obtaining (or constructing) type classes of algebraic types.
There is exactly one builder trait for each base trait in the parent directory.
For example, `EuclideanRingBuilder` contains, among other things,
evidence that `Int` and `Long` are both `EuclideanRing`.

### Intended usage

The end user should not directly reference this package.
Instead, he/she should refer to the relevant companion object.
Everything contained here is available there.

For any structure, e.g. `Ring`, use the following import statement
to receive all implementations of that structure for in-built types:
```scala 3
import io.github.sgtswagrid.nonsense.algebra.<Structure>.{*, given}
```

### Inheritance structure

#### Companion objects

Each trait herein is extended by the companion object of the corresponding typeclass.
The reason for having a separate hierarchy here is that companion objects,
by virtue of being objects, cannot form subtyping relations among themselves.

#### Internal hierarchy

**IMPORTANT:** The inheritance hierarchy between the builder traits
is in the **reverse** direction to that of the corresponding base traits.
That is, for example, _OrderedSemifieldBuilder_ extends _OrderedFieldBuilder_
if and only if _OrderedField_ extends _OrderedSemifield_.

The reason is that builders for more specific things
are also builders for more general things
(i.e. builders are contravariant in the type being constructed).
This way, we avoid duplication.

### Design goals

#### Avoidance of extra imports in user code

Imagine that you're writing a library with some generic function `f`:
```scala 3
def f[X : AdditiveMonoid](x: X): X = ???
```

Furthermore, suppose that most of the time, the user will select
for `X` some basic, in-built type such as `Int` or `Float`.
Ordinarily, the user would be required to provide evidence of `Int` or `Float`
being a `AdditiveMonoid` at the call-site, presumably with an additional import statement.
This adds overhead for the user and decreases usability,
especially for new users who may not be familiar with Scala or this kind of pattern.

Assume furthermore that, in your design, you're unwilling to sacrifice generality for this reason alone.
You may also prefer precise algebraic types over the in-built `Numeric`, `Integral`, and `Fractional`,
on account of the increased specificity.
What are you to do? Can you have your cake and eat it too? Actually, yes!

When looking for a given of type `X`, the scala compiler will look in,
among other places, the companion object of `X`.
This applies even if the user never imported anything to do with `X` at the call-site.
A key limitation, however, is that this doesn't apply to the companion objects of
other types _related_ to `X` by inheritance.

Say the user wishes to call `f` with `X = Int`.
A `EuclideanRing` is defined for `Int`,
which as a subtype of `AdditiveMonoid` definitely suffices.
However, if this is only defined in the companion object for `EuclideanRing`,
and in particular _not_ in the companion object for `AdditiveMonoid`,
then the compiler won't find it without an additional import!

This package exists as a work-around, whereby the givens associated with all subtypes
are automatically included in the companion object of the supertype.
