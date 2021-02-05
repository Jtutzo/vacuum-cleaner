package com.jtutzo.ihoover.vacuumcleaner.domain.model

data class Grid(val endPosition: Position, val startPosition: Position = Position.ZERO) {
    fun contain(position: Position) =
        startPosition.isEqualOrLessThan(position)
            .and(endPosition.isEqualOrGreaterThan(position))
}

