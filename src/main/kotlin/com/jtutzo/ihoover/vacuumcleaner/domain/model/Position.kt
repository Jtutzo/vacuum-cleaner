package com.jtutzo.ihoover.vacuumcleaner.domain.model

import com.jtutzo.ihoover.vacuumcleaner.domain.model.Orientation.*

data class Position(private val x: Int, private val y: Int) {

    companion object {
        private const val INCR_POSITION_VALUE = 1
    }

    fun advanceTowards(orientation: Orientation): Position = when (orientation) {
        NORTH -> this.copy(y = y.plus(INCR_POSITION_VALUE))
        EAST -> this.copy(x = x.minus(INCR_POSITION_VALUE))
        SOUTH -> this.copy(y = y.minus(INCR_POSITION_VALUE))
        WEST -> this.copy(x = x.plus(INCR_POSITION_VALUE))
    }

    fun isGreaterThan(position: Position): Boolean = this.x > position.x || this.y > position.y
    fun isLessThan(position: Position): Boolean = this.x < position.x || this.y < position.y

}
