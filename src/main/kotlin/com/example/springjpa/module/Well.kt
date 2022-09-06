package com.example.springjpa.module

import org.hibernate.annotations.Type
import javax.persistence.*


@Entity
class Well(var name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var id: WellId? = null
}
