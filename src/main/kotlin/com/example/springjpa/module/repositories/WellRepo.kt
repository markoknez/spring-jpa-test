package com.example.springjpa.module.repositories

import com.example.springjpa.module.ComplexId
import com.example.springjpa.module.Well
import com.example.springjpa.module.WellId
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

interface WellRepo : CrudRepository<Well, WellId> {
    @Query("select well.name from Well well " +
            "left join Scenario s on s.id = well.id " +
            "where well.id = :wellId")
    fun funnyGetById(wellId: WellId): Collection<String>
}

@Component
class WellRepo2(private val entityManager: EntityManager) {
    //    @Query(
//        "select well.name from well " +
//                "left join scenario on scenario.id = well.id " +
//                "where well.id = :complexId.id",
//        nativeQuery = true
//    )
    @Suppress("UNCHECKED_CAST")
    fun funnyGetById(complexId: ComplexId): Collection<String> {
        val query = entityManager.createNativeQuery(
            "select well.name from well " +
                    "left join scenario on scenario.id = well.id " +
                    "where well.id = :complexId"
        )
        query.setParameter("complexId", complexId.id)
        return query.resultList as Collection<String>
    }
}
