package com.jtutzo.ihoover.vacuumcleaner.domain.model

import com.jtutzo.ihoover.vacuumcleaner.domain.model.Orientation.*

data class Position(val x: Int, val y: Int) {

    companion object {
        val ZERO = Position(0, 0)
        private const val INCR_POSITION_VALUE = 1
    }

    fun advanceTowards(orientation: Orientation): Position = when (orientation) {
        NORTH -> this.copy(y = y.plus(INCR_POSITION_VALUE))
        EAST -> this.copy(x = x.minus(INCR_POSITION_VALUE))
        SOUTH -> this.copy(y = y.minus(INCR_POSITION_VALUE))
        WEST -> this.copy(x = x.plus(INCR_POSITION_VALUE))
    }

    fun isEqualOrLessThan(position: Position) = (x <= position.x).and(y <= position.y)
    fun isEqualOrGreaterThan(position: Position) = (x >= position.x).and(y >= position.y)

}