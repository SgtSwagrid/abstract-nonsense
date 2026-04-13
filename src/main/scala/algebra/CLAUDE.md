# CLAUDE.md

This file provides guidance to [Claude Code](https://claude.com/product/claude-code) when working with code in this directory.
It is not intended for human eyes.

## Instructions

* Read the [README.md](README.md) from this package before proceeding,
  in addition to those of the subpackages [builder](builder/README.md) and [ops](ops/README.md).
* This package contains type classes for algebraic structures.
* Take care to ensure inheritance relations are followed in the correct direction.
  These are subtle and can be counterintuitive.
* In particular, note that inheritance is _covariant_ for the type classes and [ops](ops), but _contravariant_ for [builder](builder).
