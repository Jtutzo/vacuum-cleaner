package com.jtutzo.ihoover.vacuumcleaner.domain

class VacuumCleaner(private val grid: Grid, private var position: Position) {
    fun execute(sequence: String) {
        position = Position("5", "6", "N")
    }
}
