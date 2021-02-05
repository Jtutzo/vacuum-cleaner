package com.jtutzo.ihoover.vacuumcleaner.domain.model

import com.jtutzo.ihoover.vacuumcleaner.domain.AdvanceInstructionStrategy
import com.jtutzo.ihoover.vacuumcleaner.domain.InstructionStrategy
import com.jtutzo.ihoover.vacuumcleaner.domain.LeftInstructionStrategy
import com.jtutzo.ihoover.vacuumcleaner.domain.RightInstructionStrategy
import com.jtutzo.ihoover.vacuumcleaner.domain.aggregate.VacuumCleanerAggregate

enum class Instruction(private val code: Char, private val instructionStrategyAsProxy: InstructionStrategy) :
    InstructionStrategy {
    LEFT('G', LeftInstructionStrategy),
    RIGHT('D', RightInstructionStrategy),
    ADVANCE('A', AdvanceInstructionStrategy);

    companion object {
        fun from(code: Char) =
            values().find { it.code == code } ?: throw IllegalArgumentException("No instruction matches with $code")
    }

    override fun applyOn(vacuumCleanerAggregate: VacuumCleanerAggregate) {
        instructionStrategyAsProxy.applyOn(vacuumCleanerAggregate)
    }
}
