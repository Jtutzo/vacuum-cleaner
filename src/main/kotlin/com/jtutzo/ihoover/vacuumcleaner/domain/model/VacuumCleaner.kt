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

    private fun changeToPreviousOrientation() = orientation.previous().also { this.orientation = it }

    private fun changeToNextOrientation() = orientation.next().also { this.orientation = it }

    private fun movePosition() = position
        .advanceTowards(orientation)
        .also { grid.verifyIfPositionIsInTheGrid(it) }
        .also { this.position = it }

}
