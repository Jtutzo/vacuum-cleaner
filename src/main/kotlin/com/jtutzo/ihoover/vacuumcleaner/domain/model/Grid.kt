package com.jtutzo.ihoover.vacuumcleaner.domain.model

import com.jtutzo.ihoover.vacuumcleaner.domain.exception.PositionIsOutOfTheGridException

data class Grid(private val endPosition: Position) {

    companion object {
        private val START_POSITION = Position(0, 0)
    }

    fun verifyIfPositionIsInTheGrid(position: Position) {
        if (position.isGreaterThan(this.endPosition) || position.isLessThan(START_POSITION))
            throw PositionIsOutOfTheGridException()
    }
}