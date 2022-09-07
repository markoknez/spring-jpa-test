package com.example.springjpa.module.repositories

import com.example.springjpa.module.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository


@Repository
public class GeneralLookupSystem {
    interface GeneralLookupTypeRepo : CrudRepository<GeneralLookupType, GeneralLookupTypeId>
    interface GeneralLookupItemRepo : CrudRepository<GeneralLookupItem, GeneralLookupItemId>
    interface GeneralLookupLanguageRepo : CrudRepository<GeneralLookupLanguage, GeneralLookupLanguageId>

    //Q what should be the id here?
    interface GeneralLookupValueRepo : CrudRepository<GeneralLookupValue, Int>

    @Component
    class Container {
        @Autowired
        lateinit var type:GeneralLookupTypeRepo

        @Autowired
        lateinit var item:GeneralLookupItemRepo

        @Autowired
        lateinit var language:GeneralLookupLanguageRepo

        @Autowired
        lateinit var values:GeneralLookupValueRepo
    }
}