package com.example.springjpa.module.repositories

import com.example.springjpa.module.Scenario
import com.example.springjpa.module.ScenarioAttributeValueId
import com.example.springjpa.module.ScenarioId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ScenarioRepo : JpaRepository<Scenario, ScenarioId>{
    @Query("Select lookup_id from scenario_attribute where scenario_id = :scenarioId", nativeQuery = true)
    fun findAttributesForScenario(scenarioId: Int):Collection<Int>
}

//interface ScenarioRepo2 : JpaRepository<Scenario2, ScenarioId>{
//}
