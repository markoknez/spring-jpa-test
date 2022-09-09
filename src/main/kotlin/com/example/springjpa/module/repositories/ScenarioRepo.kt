package com.example.springjpa.module.repositories

import com.example.springjpa.module.Scenario
import com.example.springjpa.module.ScenarioId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ScenarioRepo : CrudRepository<Scenario, ScenarioId>{

//    @Query("SELECT s FROM scenario s JOIN scenario_attribute sa on s.id = sa.scenarioId where s.id = :id")
//    fun findByIdWithAttributes(id: Int): Scenario
    /*@Modifying
    @Query("delete from scenario_attribute where scenarioId = :scenarioId")
    fun deleteAttributesById(scenarioId: Int)*/
}
