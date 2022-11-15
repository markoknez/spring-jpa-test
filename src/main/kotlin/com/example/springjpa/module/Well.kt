package com.example.springjpa.module

import org.hibernate.Hibernate
import org.hibernate.annotations.Type
import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import javax.persistence.*



@Entity
data class Well(
    var name: String
)  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var id: WellId? = null
//    override fun equals(other: Any?): Boolean {
//        return super.equals(other)
//    }
//    override fun hashCode(): Int {
//        return super.hashCode() + name.hashCode()
//    }
//    @Override
//    override fun toString(): String {
//        return this::class.simpleName + "(id=${super.id},name=$name)"
//    }

}
