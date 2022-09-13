package com.example.springjpa.module

import com.example.springjpa.module.ScenarioId
import com.example.springjpa.module.Well
import org.hibernate.annotations.Type
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "rig")
class Rig(
    var name: String,
    var description: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var id: RigId? = null

    @Column(name="build_date")
    var buildDate: Date? = null
}


