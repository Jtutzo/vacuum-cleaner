package com.jtutzo.ihoover.vacuumcleaner.domain.model

import com.jtutzo.ihoover.vacuumcleaner.domain.exception.PositionIsOutOfTheGridException

class VacuumCleaner(private val grid: Grid, private var position: Position, private var orientation: Orientation) {

    fun execute(sequence: List<Char>) {
        sequence
            .map(InstructionStrategy::from)
            .forEach { it.executeOn(this) }
    }

    fun changeToPreviousOrientation() {
        this.orientation = orientation.previous()
    }

    fun changeToNextOrientation() {
        this.orientation = orientation.next()
    }

    fun movePosition() {
        position
            .advanceTowards(orientation)
            .also(::checkPosition)
            .let { position = it }
    }

    private fun checkPosition(position: Position) {
        if (grid.contain(position).not()) {
            throw PositionIsOutOfTheGridException()
        }
    }

}
