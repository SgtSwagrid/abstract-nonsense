<div align="center">
  <h1>🌀 Abstract Nonsense</h1>
  <p>A collection of category-theoretic base traits in Scala.</p>
    <span>
    <a href="https://github.com/SgtSwagrid/abstract-nonsense/actions/workflows/build-integrity.yml"><img src="https://github.com/SgtSwagrid/abstract-nonsense/actions/workflows/build-integrity.yml/badge.svg" alt="Build status" /></a>
    <a href="https://search.maven.org/artifact/io.github.sgtswagrid/abstract-nonsense_3"><img src="https://img.shields.io/maven-central/v/io.github.sgtswagrid/abstract-nonsense_3.svg" alt="Maven Central" /></a>
  </span>
</div>

## 📋 What's included?
(This list will be continually expanded.)

1. Many varieties of [functor](https://en.wikipedia.org/wiki/Functor) (covariant, contravariant, bivariant, bounded).
2. An assortment of operators for functors, including [transformers](https://en.wikipedia.org/wiki/Monad_transformer), conditionals, numeric transformations, caching, etc.

## ⬇️ Installation

Add the following dependency to your `build.sbt`:

```scala
libraryDependencies += "io.github.sgtswagrid" %% "abstract-nonsense" % "0.1.0"
```

Compiled with Scala `3.8.3`, with no intention to explicitly support older versions.

## 📢 Publishing workflow

GitHub releases are automatically published to [Maven Central](https://central.sonatype.com/) upon creation, using [`sbt-dynver`](https://github.com/sbt/sbt-dynver).

### Example

To release version `1.2.3`, go to **Releases → Draft a new release**, create the tag `v1.2.3`, and click **Publish release**.
Note the inclusion of `v` in the GitHub release name but not the resulting Maven version.
