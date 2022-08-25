package com.example.springjpa.module.repositories

import com.example.springjpa.module.ComplexId
import com.example.springjpa.module.Well
import com.example.springjpa.module.WellId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import javax.persistence.EntityManager

interface WellRepo : CrudRepository<Well, WellId> {
    @Query("select well.name from Well well " +
            "left join Scenario s on s.id = well.id " +
            "where well.id = :wellId")
    fun wellLeftJoinWithScenarioOnId(wellId: WellId): String?

    @Query("select well.* from Well well " +
            "inner join test t on t.alsoName = well.name " +
            "where well.name = :name", nativeQuery = true)
    fun wellInnerJoinWithTestOnName(name: String): Collection<Well>

    @Query("select well.* from well where well.id = :#{#wellId.id}", nativeQuery = true)
    fun nativeQueryWithComplexId(wellId: WellId): Collection<Well>
}
