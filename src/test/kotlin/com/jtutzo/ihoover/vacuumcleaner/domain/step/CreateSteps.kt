package com.jtutzo.ihoover.vacuumcleaner.domain.step

import com.jtutzo.ihoover.vacuumcleaner.adaptator.respository.InMemoryVacuumCleanerRepository
import com.jtutzo.ihoover.vacuumcleaner.domain.Context
import com.jtutzo.ihoover.vacuumcleaner.domain.model.VacuumCleaner
import com.jtutzo.ihoover.vacuumcleaner.domain.service.VacuumCleanerService
import io.cucumber.java.Transpose
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.*

class CreateSteps {

    private var vacuumCleaner: VacuumCleaner? = null
    private val vacuumCleanerRepository = InMemoryVacuumCleanerRepository()
    private val vacuumCleanerService = VacuumCleanerService(vacuumCleanerRepository)

     @Given("a vacuum cleaner already created:")
     fun aVacuumCleanerAlreadyCreated(@Transpose vacuumCleaner: VacuumCleaner) {
         vacuumCleanerService.create(vacuumCleaner)
     }

    @When("i create a vacuum cleaner:")
    fun iCreateAVacuumCleaner(@Transpose vacuumCleaner: VacuumCleaner) {
        try {
            vacuumCleanerService.create(vacuumCleaner)
            this.vacuumCleaner = vacuumCleaner
        } catch (error: Throwable) {
            Context.error = error
        }
    }

    @Then("the vacuum cleaner is created")
    fun theVacuumCleanerIsCreated() {
        assertThat(vacuumCleanerRepository.vacuumCleaners).contains(vacuumCleaner)
    }

    @Then("the vacuum cleaner is not created")
    fun theVacuumCleanerIsNotCreated() {
        assertThat(vacuumCleanerRepository.vacuumCleaners).doesNotContain(vacuumCleaner)
    }
}