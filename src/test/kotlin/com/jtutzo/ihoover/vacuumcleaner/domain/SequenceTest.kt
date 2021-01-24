package com.jtutzo.ihoover.vacuumcleaner.domain

import org.assertj.core.api.Assertions
import org.junit.Test

class SequenceTest {

    @Test
    fun `should factory list of instructions`() {
        val sequence = "D1A2D1"
        val instructions = factoryInstructions(sequence)

        Assertions.assertThat(instructions).containsExactly(
            Right(1),
            Advance(2),
            Right(1)
        )
    }

    @Test
    fun `Should display last command executed by vacuum cleaners`() {
        val vc1 =
            VC("DADGDDDDDDDDDDDDDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEEEEEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRRRRRFFFFFFFFFFFF")
        val vc2 = VC("GAAD")
        val threadVc1 = Thread(vc1)
        val threadVc2 = Thread(vc2)

        threadVc1.start()
        threadVc2.start()

        threadVc1.join()
        threadVc2.join()

        Assertions.assertThat(vc1).extracting("lastCommand").isEqualTo("F")
        Assertions.assertThat(vc2).extracting("lastCommand").isEqualTo("D")
    }

    private fun factoryInstructions(sequence: String): List<Instruction> =
        "[ARD]\\d+"
            .toRegex()
            .findAll(sequence)
            .fold(emptyList()) { acc, instruction -> acc.plus(factoryInstruction(instruction.value)) }

    private fun factoryInstruction(element: String): Instruction {
        val value = element
            .toList()
            .drop(1)
            .map { it.toString() }
            .reduce { acc, s -> "$acc$s" }
            .toInt()
        return when (element[0]) {
            'D' -> Right(value)
            'G' -> Left(value)
            'A' -> Advance(value)
            else -> throw Exception()
        }
    }


}

class VC(private val sequence: String) : Runnable {

    var lastCommand: String? = null

    override fun run() {
        sequence.forEach {
            lastCommand = it.toString()
        }
    }

}

sealed class Instruction
data class Right(val value: Int) : Instruction()
data class Left(val value: Int) : Instruction()
data class Advance(val value: Int) : Instruction()
