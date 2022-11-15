package com.example.springjpa.module

import java.io.Serializable

data class ScenarioId(override val clientUid: String, override val id: Int) : ComplexId
data class WellId(override val clientUid: String, override val id: Int) : ComplexId
data class RigId(override val clientUid: String, override val id: Int) : ComplexId
data class GeneralLookupTypeId(override val clientUid: String, override val id: Int) : ComplexId
data class GeneralLookupItemId(override val clientUid: String, override val id: Int) : ComplexId
data class CommentId(override val clientUid: String, override val id: Int) : ComplexId
data class GeneralLookupLanguageId(override val clientUid: String, override val id: Int) : ComplexId
data class ScenarioAttributeValueId(override val clientUid: String, override val id: Int) : ComplexId

interface ComplexId : Serializable {
    val clientUid: String
    val id: Int
}
