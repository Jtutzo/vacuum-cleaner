package com.jtutzo.ihoover.vacuumcleaner.domain.step

import com.jtutzo.ihoover.vacuumcleaner.domain.model.*
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.*

class VacuumCleanerSteps {

    private var vacuumCleaner: VacuumCleaner? = null
    private var error: Throwable? = null

    @Given("I create an vacuum cleaner with {string} as size grid, {string} as initial position and {string} as initial orientation")
    fun iCreateAnVacuumCleanerBis(gridSizeInStr: String, initialPositionInStr: String, initialOrientationInStr: String) {
        val grid = Grid(factoryPosition(gridSizeInStr))
        val initialPosition = factoryPosition(initialPositionInStr)
        val initialOrientation = Orientation.from(initialOrientationInStr)
        vacuumCleaner = VacuumCleaner(grid, initialPosition, initialOrientation)
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
    fun theNewPositionOfVacuumCleanerIs(finalPositionInStr: String) {
        val finalPosition = factoryPosition(finalPositionInStr)
        assertThat(vacuumCleaner).isNotNull
        assertThat(vacuumCleaner).extracting("position").isEqualTo(finalPosition)
    }

    @Then("the new orientation of vacuum cleaner is {string}")
    fun theNewOrientationOfVacuumCleanerIs(finalOrientationInStr: String) {
        val finalOrientation = Orientation.from(finalOrientationInStr)
        assertThat(vacuumCleaner).isNotNull
        assertThat(vacuumCleaner).extracting("orientation").isEqualTo(finalOrientation)
    }

    @Then("should display {string} error")
    fun shouldDisplayError(errorMessage: String) {
        assertThat(error).isNotNull
        assertThat(error).hasMessage(errorMessage)
    }
}

private fun factoryPosition(position: String) = position
    .split(",")
    .let { Position(it[0].toInt(), it[1].toInt()) }

private fun factorySequence(sequence: String) = sequence.map { Instruction.from(it.toString()) }