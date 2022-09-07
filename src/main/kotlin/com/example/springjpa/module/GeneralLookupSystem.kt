package com.example.springjpa.module

import org.hibernate.annotations.Type
import java.io.Serializable
import javax.persistence.*

@Entity
class GeneralLookupType(var name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var id: GeneralLookupTypeId? = null
}

@Entity
class GeneralLookupItem(var shortName: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var id: GeneralLookupItemId? = null

    @ManyToOne
    var type: GeneralLookupType? = null

}

@Entity
class GeneralLookupLanguage(var name: String, var cultureCode: String, var isDefault: Boolean) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var id: GeneralLookupLanguageId? = null


}

//Q: This should be given into the constructor, or enough later?
//Q: Since this dont have Id what should we do?
@Entity
//@IdClass(GeneralLookupValueId::class)
class GeneralLookupValue(
    @Id var lookupId: GeneralLookupItemId, @Id var langId: GeneralLookupLanguageId, var name: String
)

//data class GeneralLookupValueId(var lookupId: GeneralLookupItem, var langId: GeneralLookupLanguageId) : Serializable