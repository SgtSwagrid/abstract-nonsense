# CLAUDE.md

This file provides guidance to [Claude Code](https://claude.com/product/claude-code) when working with code in this directory.
It is not intended for human eyes.

## Instructions

* Read the [README.md](README.md) from this package before proceeding.
* In particular, note that the inheritance hierarchy between the operator traits
  is in the same direction to that of the corresponding base traits.
  That is, for example, _OrderedFieldOps_ extends _OrderedSemifieldOps_
  if and only if _OrderedField_ extends _OrderedSemifield_.
* This is in stark contrast to the behaviour in the sibling directory [builder](../builder).
