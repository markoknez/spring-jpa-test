package com.example.springjpa.module.repositories

import com.example.springjpa.module.Scenario
import com.example.springjpa.module.ScenarioId
import org.springframework.data.repository.CrudRepository

interface ScenarioRepo : CrudRepository<Scenario, ScenarioId>
