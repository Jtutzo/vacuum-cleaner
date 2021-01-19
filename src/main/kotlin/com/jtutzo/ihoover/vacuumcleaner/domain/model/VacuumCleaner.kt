package com.jtutzo.ihoover.vacuumcleaner.domain.model

class VacuumCleaner(private val grid: Grid, private var position: Position) {

    fun execute(sequence: List<Instruction>) {
        sequence.forEach(::execute)
    }

    private fun execute(instruction: Instruction) {
        position
            .calculateNewPosition(instruction)
            .also { grid.verifyIsOnTheGrid(it) }
            .let { this.position = it }
    }
}
