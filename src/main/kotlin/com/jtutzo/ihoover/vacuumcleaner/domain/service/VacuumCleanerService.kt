package com.jtutzo.ihoover.vacuumcleaner.domain.service

import com.jtutzo.ihoover.vacuumcleaner.domain.exception.AlreadyCreatedException
import com.jtutzo.ihoover.vacuumcleaner.domain.model.VacuumCleaner
import com.jtutzo.ihoover.vacuumcleaner.domain.repository.VacuumCleanerRepository

class VacuumCleanerService(private val vacuumCleanerRepository: VacuumCleanerRepository) {
    fun create(vacuumCleaner: VacuumCleaner) {
        if (vacuumCleanerRepository.containName(vacuumCleaner.name)) {
            throw AlreadyCreatedException(vacuumCleaner.name)
        }
        vacuumCleanerRepository.create(vacuumCleaner)
    }
}
