package com.jtutzo.ihoover.vacuumcleaner.adaptator.respository

import com.jtutzo.ihoover.vacuumcleaner.domain.model.VacuumCleaner
import com.jtutzo.ihoover.vacuumcleaner.domain.repository.VacuumCleanerRepository

class InMemoryVacuumCleanerRepository : VacuumCleanerRepository {

    val vacuumCleaners = mutableListOf<VacuumCleaner>()

    override fun create(vacuumCleaner: VacuumCleaner) {
        vacuumCleaners.add(vacuumCleaner)
    }

    override fun containName(name: String): Boolean = vacuumCleaners
        .find { it.name == name }
        ?.let { true }
        ?: false

}
