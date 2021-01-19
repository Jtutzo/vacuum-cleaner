package com.jtutzo.ihoover.vacuumcleaner.domain.model

import com.jtutzo.ihoover.vacuumcleaner.domain.exception.OutOfGridException

data class Grid(val x: Int, val y: Int) {

    fun verifyIsOnTheGrid(position: Position) {
        if (positionXIsOutOfGrid(position.x) || positionYIsOutOfGrid(position.y))
            throw OutOfGridException()
    }

    private fun positionXIsOutOfGrid(x: Int) = x < 0 || x > this.x
    private fun positionYIsOutOfGrid(y: Int) = y < 0 || y > this.y
}