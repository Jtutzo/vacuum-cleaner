package com.jtutzo.ihoover.vacuumcleaner.domain.model

fun interface InstructionStrategy {

    companion object {
        fun from(instruction: Char) = when (instruction) {
            'G' -> LeftInstructionStrategy
            'D' -> RightInstructionStrategy
            'A' -> AdvanceInstructionStrategy
            else -> throw IllegalArgumentException("No strategy for instruction : $instruction")
        }
    }

    fun executeOn(vacuumCleaner: VacuumCleaner)
}

object LeftInstructionStrategy : InstructionStrategy {
    override fun executeOn(vacuumCleaner: VacuumCleaner) {
        vacuumCleaner.changeToPreviousOrientation()
    }
}

object RightInstructionStrategy : InstructionStrategy {
    override fun executeOn(vacuumCleaner: VacuumCleaner) {
        vacuumCleaner.changeToNextOrientation()
    }
}

object AdvanceInstructionStrategy : InstructionStrategy {
    override fun executeOn(vacuumCleaner: VacuumCleaner) {
        vacuumCleaner.movePosition()
    }
}
