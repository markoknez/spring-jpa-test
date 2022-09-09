package com.example.springjpa.module

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import org.hibernate.annotations.Fetch
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

    //Q: should we store attributes or ids?
    //scenario.rig.id -> check if trigger additional statement
    @ManyToOne
    var rig: Rig? = null
    //check embeddable/element connection, before using it
// not works, if ScenarioAttributes are entities
//    @Embedded
//   @AttributeOverrides(
//        AttributeOverride(name="scenarioId", column = Column(name="scenarioId", insertable = false, updatable = false))
//    )
// works when ScenarioAttributes not entities so they cannot be deleted independently

    @ElementCollection
    @CollectionTable(name = "scenario_attribute", joinColumns = [JoinColumn(name="scenarioId")])
    @Column(name="lookupId")
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var attributes: MutableSet<ScenarioAttributeValueId>? = null

//    @OneToMany(cascade = [CascadeType.ALL])
//    var attributes: List<ScenarioAttribute>? = null

}

// Q on this level should we represent dal or bl?
/*@Entity
@IdClass(ScenarioAttributeId::class)
//use Type annotation like line 12
class ScenarioAttribute(
    @Id
    @Column(nullable = false)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType") var scenarioId: ScenarioId? = null, @Id
    @Column(nullable = false)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType") var lookupId: GeneralLookupItemId? = null
)*/

/*
@Embeddable
@Table(name="scenario_attribute")
class ScenarioAttribute(
    @Id
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var scenarioId: ScenarioId? = null,
    @Id
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var lookupId: GeneralLookupItemId? = null,

//    @ManyToOne
//    put lazy
//    @JoinColumn(name = "scenarioId", nullable = false, insertable = false, updatable = false)
//    var scenario:Scenario? = null
) : Serializable
*/
/*
@Embeddable
class ScenarioAttributeId : Serializable {
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var scenarioId: ScenarioId? = null

    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var lookupId: GeneralLookupItemId? = null
}
*/
//@Entity
//class ScenarioAttribute {
//    @EmbeddedId
//    val scenarioAttributeId: ScenarioAttributeId = ScenarioAttributeId()
//
//    @MapsId("scenarioId")
//    @JoinColumn(insertable = false, updatable = false)
//    var scenario: Scenario? = null
//
//    @MapsId("lookupId")
//    @JoinColumn(insertable = false, updatable = false)
//    var lookupId: GeneralLookupItem? = null
//}
