package com.jtutzo.ihoover.vacuumcleaner.domain

import com.jtutzo.ihoover.vacuumcleaner.domain.model.Instruction
import com.jtutzo.ihoover.vacuumcleaner.domain.model.Position

fun factoryPosition(position: String) = position
    .split(",")
    .let { Position(it[0].toInt(), it[1].toInt()) }

fun factorySequence(sequence: String) = sequence.map(Instruction::from)
