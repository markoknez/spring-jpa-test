package com.example.springjpa.module

import org.hibernate.annotations.Type
import java.io.Serializable
import javax.persistence.*

@Entity
class Scenario(var name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var id: ScenarioId? = null

    @ManyToOne
    var well: Well? = null

    @ManyToOne
    var rig: Rig? = null
    @ManyToMany(targetEntity = ScenarioAttribute::class)
    var attributes: List<ScenarioAttribute> = listOf()
}

// Q on this level should we represent dal or bl?
@Entity
@IdClass(ScenarioAttributeId::class)
class ScenarioAttribute(@Id var scenarioId: ScenarioId, @Id var lookupId: GeneralLookupItemId)

data class ScenarioAttributeId(@Id var scenarioId: ScenarioId, @Id var lookupId: GeneralLookupItemId) : Serializable


