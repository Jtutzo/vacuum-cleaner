package com.jtutzo.ihoover.vacuumcleaner.domain.repository

import com.jtutzo.ihoover.vacuumcleaner.domain.model.VacuumCleaner

interface VacuumCleanerRepository {
    fun create(vacuumCleaner: VacuumCleaner)
    fun containName(name: String): Boolean

}
