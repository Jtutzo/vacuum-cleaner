package com.jtutzo.ihoover.vacuumcleaner.domain

class VacuumCleaner(private val grid: Grid, private var position: Position) {

    fun execute(sequence: List<Instruction>) = sequence
        .fold(position) { position, instruction -> position.execute(instruction) }
        .also { position = it }

}
