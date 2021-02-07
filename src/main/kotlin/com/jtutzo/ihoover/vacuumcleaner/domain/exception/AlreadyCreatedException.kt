package com.jtutzo.ihoover.vacuumcleaner.domain.exception

class AlreadyCreatedException(vacuumCleanerName: String) :
    Throwable("The vacuum cleaner $vacuumCleanerName is already created")
