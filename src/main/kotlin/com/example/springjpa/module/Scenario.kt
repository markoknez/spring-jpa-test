package com.example.springjpa.module

import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
class Scenario(var name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var id: ScenarioId? = null

    @ManyToOne
    var well: Well? = null
}
