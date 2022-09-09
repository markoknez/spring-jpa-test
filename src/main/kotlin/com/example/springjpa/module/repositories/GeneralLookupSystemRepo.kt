package com.example.springjpa.module.repositories

import com.example.springjpa.module.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository


@Repository
public class GeneralLookupSystem() {
    interface GeneralLookupTypeRepo : CrudRepository<GeneralLookupType, GeneralLookupTypeId>
    interface GeneralLookupItemRepo : CrudRepository<GeneralLookupItem, GeneralLookupItemId>
    interface GeneralLookupLanguageRepo : CrudRepository<GeneralLookupLanguage, GeneralLookupLanguageId>

    //Q what should be the id here?
    interface GeneralLookupValueRepo : CrudRepository<GeneralLookupValue, GeneralLookupValueId>

    @Component
    class Container(
        val type: GeneralLookupTypeRepo,
        val item: GeneralLookupItemRepo,
        val language: GeneralLookupLanguageRepo,
        val values: GeneralLookupValueRepo
    )
}