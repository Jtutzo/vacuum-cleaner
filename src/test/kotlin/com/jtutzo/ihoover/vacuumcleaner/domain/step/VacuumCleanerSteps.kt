package com.jtutzo.ihoover.vacuumcleaner.domain.step

import com.jtutzo.ihoover.vacuumcleaner.domain.Grid
import com.jtutzo.ihoover.vacuumcleaner.domain.Orientation
import com.jtutzo.ihoover.vacuumcleaner.domain.Position
import com.jtutzo.ihoover.vacuumcleaner.domain.VacuumCleaner
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.*

class VacuumCleanerSteps {

    private var vacuumCleaner: VacuumCleaner? = null

    @Given("i create an vacuum cleaner with {string} as size grid and {string} as initial position")
    fun iCreateAnVacuumCleaner(gridSizeToString: String, initialPositionToString: String) {
        val grid = factoryGrid(gridSizeToString)
        val initialPosition = factoryPosition(initialPositionToString)
        vacuumCleaner = VacuumCleaner(grid, initialPosition)
    }

    @When("execute the sequence {string}")
    fun executeTheSequence(sequence: String) {
        assertThat(vacuumCleaner).isNotNull
        vacuumCleaner?.execute(sequence)
    }

    @Then("the new position of vacuum cleaner is {string}")
    fun theNewPositionOfVacuumCleanerIs(finalPositionToString: String) {
        val finalPosition = factoryPosition(finalPositionToString)
        assertThat(vacuumCleaner).isNotNull
        assertThat(vacuumCleaner).extracting("position").isEqualTo(finalPosition)
    }
}

private fun factoryGrid(gridSize: String) = gridSize
    .split(",")
    .let { Grid(it[0].toInt(), it[1].toInt()) }

private fun factoryPosition(position: String) = position
    .split(",")
    .let { Position(it[0].toInt(), it[1].toInt(), Orientation.from(it[2])) }