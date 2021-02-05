package com.jtutzo.ihoover.vacuumcleaner.domain.aggregate

import com.jtutzo.ihoover.vacuumcleaner.domain.exception.PositionIsOutOfTheGridException
import com.jtutzo.ihoover.vacuumcleaner.domain.model.*

class VacuumCleanerAggregate(state: VacuumCleaner) {

    private val initialState = state

    private var position: Position = state.position

    private var orientation: Orientation = state.orientation

    fun execute(sequence: List<Instruction>) {
        sequence.forEach { it.applyOn(this) }
    }

    fun getCurrentlyState(): VacuumCleaner = initialState.copy(position = position, orientation = orientation)

    internal fun changeToPreviousOrientation() {
        this.orientation = orientation.previous()
    }

    internal fun changeToNextOrientation() {
        this.orientation = orientation.next()
    }

    internal fun movePosition() {
        position
            .advanceTowards(orientation)
            .also(::checkPosition)
            .let(::position::set)
    }

    private fun checkPosition(position: Position) {
        if (initialState.grid.contain(position).not()) {
            throw PositionIsOutOfTheGridException()
        }
    }

}
