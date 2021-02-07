package com.jtutzo.ihoover.vacuumcleaner.domain.step

import com.jtutzo.ihoover.vacuumcleaner.domain.Context
import com.jtutzo.ihoover.vacuumcleaner.domain.aggregate.VacuumCleanerAggregate
import com.jtutzo.ihoover.vacuumcleaner.domain.factoryPosition
import com.jtutzo.ihoover.vacuumcleaner.domain.factorySequence
import com.jtutzo.ihoover.vacuumcleaner.domain.model.*
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat

class ExecuteSequenceSteps {

    private val context = Context
    private var initialState: VacuumCleaner? = null
    private var currentState: VacuumCleaner? = null

    @Given("I create an vacuum cleaner with {string} as size grid, {string} as initial position and {string} as initial orientation")
    fun iCreateAnVacuumCleaner(gridSizeInStr: String, initialPositionInStr: String, initialOrientationInStr: String) {
        val grid = Grid(factoryPosition(gridSizeInStr))
        val initialPosition = factoryPosition(initialPositionInStr)
        val initialOrientation = Orientation.from(initialOrientationInStr)
        initialState = VacuumCleaner("name", grid, initialPosition, initialOrientation)
    }

    @When("execute the sequence {string}")
    fun executeTheSequence(sequenceInStr: String) {
        val sequence = factorySequence(sequenceInStr)
        assertThat(initialState).isNotNull
        try {
            val vacuumCleanerAggregate = VacuumCleanerAggregate(initialState!!)
            vacuumCleanerAggregate.execute(sequence)
            currentState = vacuumCleanerAggregate.getCurrentlyState()
        } catch (exception: Throwable) {
            context.error = exception
        }
    }

    @Then("the new position and orientation of vacuum cleaner are {string} and {string}")
    fun theNewPositionANdOrientationOfVacuumCleanerAre(finalPositionInStr: String, finalOrientationInStr: String) {
        val finalPosition = factoryPosition(finalPositionInStr)
        val finalOrientation = Orientation.from(finalOrientationInStr)
        assertThat(initialState).isNotNull
        assertThat(currentState).isNotNull
        val expected = initialState!!.copy(position = finalPosition, orientation = finalOrientation)
        assertThat(currentState).isEqualTo(expected)
    }

}