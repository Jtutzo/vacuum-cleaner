package com.jtutzo.ihoover.vacuumcleaner.domain.step

import com.jtutzo.ihoover.vacuumcleaner.domain.factoryPosition
import com.jtutzo.ihoover.vacuumcleaner.domain.model.Grid
import com.jtutzo.ihoover.vacuumcleaner.domain.model.Orientation
import com.jtutzo.ihoover.vacuumcleaner.domain.model.VacuumCleaner
import io.cucumber.java.DataTableType

@DataTableType
fun vacuumCleanerEntryTransformer(entry: Map<String, String>): VacuumCleaner {
    return VacuumCleaner(
        entry["name"]!!,
        Grid(factoryPosition(entry["positionEndGrid"]!!)),
        factoryPosition(entry["position"]!!),
        Orientation.from(entry["orientation"]!!)
    )
}
