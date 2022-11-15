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
class GeneralLookupItem(
    @Column(name = "shortname")
    var shortName: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var id: GeneralLookupItemId? = null


    @ManyToOne
    @JoinColumn(name = "typeid", nullable = false)
    var typeId: GeneralLookupType? = null

}

@Entity
class GeneralLookupLanguage(
    var name: String,
    @Column(name = "culturecode") var cultureCode: String,
    @Column(name = "isdefault") var isDefault: Boolean
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    var id: GeneralLookupLanguageId? = null


}

//Q: This should be given into the constructor, or enough later?
//Q: Since this dont have Id what should we do?
@Entity
@IdClass(GeneralLookupValueId::class)
class GeneralLookupValue(@Id @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType") @Column(name = "lookupid") var lookupId: GeneralLookupItemId,
                         @Id @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType") @Column(name = "langid") var langId: GeneralLookupLanguageId,
                         var name: String)


class GeneralLookupValueId() : Serializable {
    @Id
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    @Column(name = "lookupid")
    var lookupId: GeneralLookupItemId? = null

    @Id
    @Type(type = "com.example.springjpa.module.customtypes.ComplexIdSingleColumnType")
    @Column(name = "langid")
    var langId: GeneralLookupLanguageId? = null
}
