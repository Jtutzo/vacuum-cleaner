package com.jtutzo.ihoover.vacuumcleaner.domain.step

import com.jtutzo.ihoover.vacuumcleaner.domain.Grid
import com.jtutzo.ihoover.vacuumcleaner.domain.Position
import com.jtutzo.ihoover.vacuumcleaner.domain.VacuumCleaner
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.*

class VacuumCleanerSteps {

    private var vacuumCleaner: VacuumCleaner? = null

    @Given("i create an vacuum cleaner with {string} and {string} as position grid and {string}, {string} and {string} as initial position")
    fun iCreateAnVacuumCleaner(gridX: String, gridY: String, vacuumCleanerX: String, vacuumCleanerY: String, vacuumCleanerOrientation: String) {
        val grid = Grid(gridX, gridY)
        val initialPosition = Position(vacuumCleanerX, vacuumCleanerY, vacuumCleanerOrientation)
        vacuumCleaner = VacuumCleaner(grid, initialPosition)
    }

    @When("execute the sequence {string}")
    fun executeTheSequence(sequence: String) {
        assertThat(vacuumCleaner).isNotNull
        vacuumCleaner?.execute(sequence)
    }

    @Then("the new position of vacuum cleaner is {string}, {string} and {string}")
    fun theNewPositionOfVacuumCleanerIs(vacuumCleanerX: String, vacuumCleanerY: String, vacuumCleanerOrientation: String) {
        val finalPosition = Position(vacuumCleanerX, vacuumCleanerY, vacuumCleanerOrientation)
        assertThat(vacuumCleaner).isNotNull
        assertThat(vacuumCleaner).extracting("position").isEqualTo(finalPosition)
    }
}