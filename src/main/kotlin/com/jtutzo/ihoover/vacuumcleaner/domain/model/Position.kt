package com.jtutzo.ihoover.vacuumcleaner.domain.model

import com.jtutzo.ihoover.vacuumcleaner.domain.model.Instruction.*
import com.jtutzo.ihoover.vacuumcleaner.domain.model.Orientation.*

data class Position(val x: Int, val y: Int, val orientation: Orientation) {

    companion object {
        private const val INCR_POSITION_VALUE = 1
    }

    fun execute(instruction: Instruction) = when (instruction) {
        LEFT -> previousOrientation()
        RIGHT -> nextOrientation()
        ADVANCE -> advance()
    }

    private fun previousOrientation(): Position = this.copy(orientation = orientation.previous())

    private fun nextOrientation(): Position = this.copy(orientation = orientation.next())

    private fun advance(): Position = when (orientation) {
        NORTH -> this.copy(y = y.plus(INCR_POSITION_VALUE))
        EAST -> this.copy(x = x.minus(INCR_POSITION_VALUE))
        SOUTH -> this.copy(y = y.minus(INCR_POSITION_VALUE))
        WEST -> this.copy(x = x.plus(INCR_POSITION_VALUE))
    }

}
