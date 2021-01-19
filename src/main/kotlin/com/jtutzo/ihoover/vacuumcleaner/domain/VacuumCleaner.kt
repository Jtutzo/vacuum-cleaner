package com.jtutzo.ihoover.vacuumcleaner.domain

class VacuumCleaner(private val grid: Grid, private var position: Position) {

    fun execute(sequence: String) {
        sequence
            .mapToInstructions()
            .fold(position) { position, instruction -> position.execute(instruction) }
            .let { position = it }
    }

}

private fun String.mapToInstructions() = this.map { Instruction.from(it.toString()) }
