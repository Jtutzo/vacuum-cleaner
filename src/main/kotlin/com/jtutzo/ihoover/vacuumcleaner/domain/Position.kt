package com.jtutzo.ihoover.vacuumcleaner.domain

import com.jtutzo.ihoover.vacuumcleaner.domain.Instruction.*
import com.jtutzo.ihoover.vacuumcleaner.domain.Orientation.*

data class Position(val x: Int, val y: Int, val orientation: Orientation) {

    companion object {
        private const val VALUE_ADVANCED = 1
    }

    fun execute(instruction: Instruction) = when (instruction) {
        LEFT -> previousOrientation()
        RIGHT -> nextOrientation()
        ADVANCE -> advance()
    }

    private fun previousOrientation(): Position = this.copy(orientation = orientation.previous())

    private fun nextOrientation(): Position = this.copy(orientation = orientation.next())

    private fun advance(): Position = when (orientation) {
        NORTH -> this.copy(y = y.plus(VALUE_ADVANCED))
        EAST -> this.copy(x = x.minus(VALUE_ADVANCED))
        SOUTH -> this.copy(y = y.minus(VALUE_ADVANCED))
        WEST -> this.copy(x = x.plus(VALUE_ADVANCED))
    }

}
