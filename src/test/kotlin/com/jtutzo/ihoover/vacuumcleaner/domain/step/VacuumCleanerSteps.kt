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
    fun iCreateAnVacuumCleaner(gridSizeInStr: String, initialPositionInStr: String, initialOrientationInStr: String) {
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

    @Then("the new position and orientation of vacuum cleaner are {string} and {string}")
    fun theNewPositionANdOrientationOfVacuumCleanerAre(finalPositionInStr: String, finalOrientationInStr: String) {
        val finalPosition = factoryPosition(finalPositionInStr)
        val finalOrientation = Orientation.from(finalOrientationInStr)
        assertThat(vacuumCleaner).isNotNull
        assertThat(vacuumCleaner).extracting("position").isEqualTo(finalPosition)
        assertThat(vacuumCleaner).extracting("orientation").isEqualTo(finalOrientation)
    }

    @Then("there is no error")
    fun thereIsNoError() {
        assertThat(error).isNull()
    }

    @Then("there is an error with message {string}")
    fun thereIsAnErrorWithMessage(errorMessage: String) {
        assertThat(error).isNotNull
        assertThat(error).hasMessage(errorMessage)
    }
}

private fun factoryPosition(position: String) = position
    .split(",")
    .let { Position(it[0].toInt(), it[1].toInt()) }

private fun factorySequence(sequence: String) = sequence.map { Instruction.from(it.toString()) }