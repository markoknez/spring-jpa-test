package com.example.springjpa.module.repositories

import com.example.springjpa.module.ComplexId
import com.example.springjpa.module.Well
import com.example.springjpa.module.WellId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface WellRepo : CrudRepository<Well, WellId> {
    @Query(
        "select well.name from Well well " +
                "inner join Scenario scenario on scenario.id = well.id " +
                "where well.id = ?1"
    )
    fun funnyGetById(complexId: ComplexId): Collection<String>
}
