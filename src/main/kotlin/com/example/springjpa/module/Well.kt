package com.example.springjpa.module

import org.hibernate.annotations.Parameter
import org.hibernate.annotations.Type
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.io.Serializable
import javax.persistence.*

interface WellRepo : CrudRepository<Well, WellId> {
    @Query(
        "select well.name from Well well " +
                "inner join Scenario scenario on scenario.id = well.id " +
                "where well.id = ?1"
    )
    fun funnyGetById(complexId: ComplexId): Collection<String>
}

interface ScenarioRepo : CrudRepository<Scenario, ScenarioId>


@Entity
class Well(var name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.ComplexIdSingleColumnType")
    var id: WellId? = null
}

@Entity
class Scenario(var name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.ComplexIdSingleColumnType")
    var id: ScenarioId? = null

    @ManyToOne
    var well: Well? = null
}

class ScenarioId(override val clientUid: String, override val id: Int) : ComplexId
class WellId(override val clientUid: String, override val id: Int) : ComplexId

interface ComplexId : Serializable {
    val clientUid: String
    val id: Int
}


