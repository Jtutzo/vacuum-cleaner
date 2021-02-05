package com.jtutzo.ihoover.vacuumcleaner.domain

import com.jtutzo.ihoover.vacuumcleaner.domain.aggregate.VacuumCleanerAggregate

internal fun interface InstructionStrategy {
    fun applyOn(vacuumCleanerAggregate: VacuumCleanerAggregate)
}

internal object LeftInstructionStrategy : InstructionStrategy {
    override fun applyOn(vacuumCleanerAggregate: VacuumCleanerAggregate) {
        vacuumCleanerAggregate.changeToPreviousOrientation()
    }
}

internal object RightInstructionStrategy : InstructionStrategy {
    override fun applyOn(vacuumCleanerAggregate: VacuumCleanerAggregate) {
        vacuumCleanerAggregate.changeToNextOrientation()
    }
}

internal object AdvanceInstructionStrategy : InstructionStrategy {
    override fun applyOn(vacuumCleanerAggregate: VacuumCleanerAggregate) {
        vacuumCleanerAggregate.movePosition()
    }
}
