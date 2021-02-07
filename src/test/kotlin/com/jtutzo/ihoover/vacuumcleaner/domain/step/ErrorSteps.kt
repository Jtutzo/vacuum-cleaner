package com.jtutzo.ihoover.vacuumcleaner.domain.step

import com.jtutzo.ihoover.vacuumcleaner.domain.Context
import io.cucumber.java.en.Then
import org.assertj.core.api.Assertions.assertThat

class ErrorSteps {

    private val context = Context

    @Then("there is no error")
    fun thereIsNoError() {
        assertThat(context.error).isNull()
    }

    @Then("there is an error with message {string}")
    fun thereIsAnErrorWithMessage(errorMessage: String) {
        assertThat(context.error).isNotNull
        assertThat(context.error).hasMessage(errorMessage)
    }
}