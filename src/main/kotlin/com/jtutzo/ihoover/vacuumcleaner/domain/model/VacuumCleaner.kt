package com.jtutzo.ihoover.vacuumcleaner.domain.model

import com.jtutzo.ihoover.vacuumcleaner.domain.model.Instruction.*

class VacuumCleaner(private val grid: Grid, private var position: Position, private var orientation: Orientation) {

    fun execute(sequence: List<Instruction>) {
        sequence.forEach(::execute)
    }

    private fun execute(instruction: Instruction) {
        when (instruction) {
            LEFT -> changeToPreviousOrientation()
            RIGHT -> changeToNextOrientation()
            ADVANCE -> movePosition()
        }
    }

    private fun changeToPreviousOrientation() {
        this.orientation = orientation.previous()
    }

    private fun changeToNextOrientation() {
        this.orientation = orientation.next()
    }

    private fun movePosition() {
        position
            .advanceTowards(orientation)
            .also { grid.verifyIfPositionIsInTheGrid(it) }
            .let { this.position = it }
    }

}
