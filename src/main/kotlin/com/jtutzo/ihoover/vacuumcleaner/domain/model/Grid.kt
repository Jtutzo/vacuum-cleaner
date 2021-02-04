package com.jtutzo.ihoover.vacuumcleaner.domain.model

data class Grid(private val endPosition: Position) {

    private val startPosition: Position = Position.ZERO

    fun contain(position: Position) =
        startPosition.isEqualOrLessThan(position)
            .and(endPosition.isEqualOrGreaterThan(position))
}

