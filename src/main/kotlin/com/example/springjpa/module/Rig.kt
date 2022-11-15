package com.example.springjpa.module

import com.example.springjpa.module.ScenarioId
import com.example.springjpa.module.Well
import org.hibernate.Hibernate
import org.hibernate.annotations.Type
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "rig")
data class Rig(
    var name: String,
    var description: String,
    @Column(name="build_date")
    var buildDate: Date? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var id: RigId? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Rig

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

//    @Override
//    override fun toString(): String {
//        return this::class.simpleName + "(id = $id , name = $name , description = $description , buildDate = $buildDate )"
//    }
}


