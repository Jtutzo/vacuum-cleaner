package com.jtutzo.ihoover.vacuumcleaner.domain.step

import com.jtutzo.ihoover.vacuumcleaner.domain.model.*
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.*

class VacuumCleanerSteps {

    private var vacuumCleaner: VacuumCleaner? = null
    private var error: Throwable? = null

    @Given("i create an vacuum cleaner with {string} as size grid and {string} as initial position")
    fun iCreateAnVacuumCleaner(gridSizeToString: String, initialPositionToString: String) {
        val grid = factoryGrid(gridSizeToString)
        val initialPosition = factoryPosition(initialPositionToString)
        vacuumCleaner = VacuumCleaner(grid, initialPosition)
    }

    @When("execute the sequence {string}")
    fun executeTheSequence(sequenceToString: String) {
        val sequence = factorySequence(sequenceToString)
        assertThat(vacuumCleaner).isNotNull
        try {
            vacuumCleaner!!.execute(sequence)
        } catch (exception: Throwable) {
            error = exception
        }
    }

    @Then("the new position of vacuum cleaner is {string}")
    fun theNewPositionOfVacuumCleanerIs(finalPositionToString: String) {
        val finalPosition = factoryPosition(finalPositionToString)
        assertThat(vacuumCleaner).isNotNull
        assertThat(vacuumCleaner).extracting("position").isEqualTo(finalPosition)
    }

    @Then("should display {string} error")
    fun shouldDisplayError(errorMessage: String) {
        assertThat(error).isNotNull
        assertThat(error).hasMessage(errorMessage)
    }
}

private fun factoryGrid(gridSize: String) = gridSize
    .split(",")
    .let { Grid(it[0].toInt(), it[1].toInt()) }

private fun factoryPosition(position: String) = position
    .split(",")
    .let { Position(it[0].toInt(), it[1].toInt(), Orientation.from(it[2])) }

private fun factorySequence(sequence: String) = sequence.map { Instruction.from(it.toString()) }