<div align="center">
  <h1>🌀 Abstract Nonsense</h1>
  <p>A collection of category-theoretic base traits in Scala.</p>
    <span>
    <a href="https://github.com/SgtSwagrid/abstract-nonsense/actions/workflows/build-integrity.yml"><img src="https://github.com/SgtSwagrid/abstract-nonsense/actions/workflows/build-integrity.yml/badge.svg" alt="Build status" /></a>
    <a href="https://search.maven.org/artifact/io.github.sgtswagrid/abstract-nonsense_3"><img src="https://img.shields.io/maven-central/v/io.github.sgtswagrid/abstract-nonsense_3.svg" alt="Maven Central" /></a>
  </span>
</div>

## 🙋🏻‍♂️ Who's this for?

_Abstract Nonsense_ is intended primarily for library design purposes.
Any library which introduces structures with `map`-like operators can benefit through decreased repetition and increased ergonomics for its end users.

## ✅ What's included?
(This list will be continually expanded.)

### 1. Many varieties of [functor](https://en.wikipedia.org/wiki/Functor):

Roughly speaking, at least from the programmer's perspective, a functor is something that can be _mapped_ over.
Common examples include lists, options, futures, streams, signals, etc.

| Kind                      | Implementation                                                                  | Purpose                                         |
| ------------------------- | ------------------------------------------------------------------------------- | ----------------------------------------------- |
| **Covariant Functor**     | [Functor.scala](src/main/scala/functor/covariant/Functor.scala)                 | For structures with `map`.                      |
| **Contravariant Functor** | [Contravariant.scala](src/main/scala/functor/contravariant/Contravariant.scala) | For structures with `contramap`.                |
| **Profunctor**            | [Profunctor.scala](src/main/scala/functor/profunctor/Profunctor.scala)           | For structures with both `map` and `contramap`. |
| **Bifunctor**             | [Bifunctor.scala](src/main/scala/functor/bifunctor/Bifunctor.scala)             | For structures with two kinds of `map`.         |
| **Applicative Functor**   | [Applicative.scala](src/main/scala/functor/applicative/Applicative.scala)       | For structures with `map` and `zip`.            |

### 2. An assortment of operators for functors:

This is where the real benefit arises. You don't need a base trait from a library to implement `map` for yourself.
However, given the existence of `map` (and/or `zip`), many other useful operators can be derived for free.
They are made available to you instantly, without any repetition across your various mappable types.

Includes [transformers](https://en.wikipedia.org/wiki/Monad_transformer), conditionals, numeric transformations, caching, etc.

## 👉 Examples

If we start from an implementation of `Functor` that defines `map`:

```scala
class List[+X](values: X*) extends Functor[List, X]:
  override protected def mapImpl[Y](f: X => Y): List[Y] = // ...
```

Then many other operators come for free:

```scala
val nested = List(List(1, 2), List(3, 4))
val mapped = nested.deep.map(_ * 2)
// mapped == List(List(2, 4), List(6, 8))
```

```scala
val mixed = List[Int | String](16, "Hello, World!", 42)
val mapped = mixed.when[Int].map(_ + 1)
// mapped == List(17, "Hello, World!", 43)
```

See [functor.covariant.ops](src/main/scala/functor/covariant/ops) for some more examples.

## ⬇️ Installation

Add the following dependency to your `build.sbt`:

```scala
libraryDependencies += "io.github.sgtswagrid" %% "abstract-nonsense" % "0.1.4"
```

Compiled with Scala `3.8.3`, with no intention to explicitly support older versions.

## ⚠️ Nomenclature


It would be more correct to say that a functor is a thing that performs a mapping.
Instead of "a list _is_ a functor", people say "a list _has_ a functor".
Indeed, for many implementations, multiple different functors can be defined for the same kind of structure.
Nevertheless, the attitude adopted here is one that prefers inheritance over typeclasses, because:
1. Each particular structure has, almost always, a _single_ canonical `map` implementation, and
2. Extension methods typically have worse IDE discoverability for the end user.

## 📢 Publishing workflow

GitHub releases are automatically published to [Maven Central](https://central.sonatype.com/) upon creation, using [`sbt-dynver`](https://github.com/sbt/sbt-dynver).

### Example

To release version `1.2.3`, go to **Releases → Draft a new release**, create the tag `v1.2.3`, and click **Publish release**.
Note the inclusion of `v` in the GitHub release name but not the resulting Maven version.

## 👁️ See also

- Check out [Not Enough Structures](https://github.com/SgtSwagrid/not-enough-structures) for some different abstractions with a focus on abstract algebra.
- This library was made using [Scala Library Template](https://github.com/SgtSwagrid/scala-library-template).
