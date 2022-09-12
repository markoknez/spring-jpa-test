package com.example.springjpa.module


import org.hibernate.annotations.LazyToOne
import org.hibernate.annotations.LazyToOneOption
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "scenario")
class Scenario(
    var name: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var id: ScenarioId? = null


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rigId")
    var rig: Rig? = null


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="wellId")
    var well: Well? = null

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "scenario_attribute", joinColumns = [JoinColumn(name = "scenarioId")])
    @Column(name = "lookupId")
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var attributes: MutableSet<ScenarioAttributeValueId> = mutableSetOf()
}


