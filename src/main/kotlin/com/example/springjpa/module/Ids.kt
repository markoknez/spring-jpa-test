package com.example.springjpa.module

import java.io.Serializable

class ScenarioId(override val clientUid: String, override val id: Int) : ComplexId
class WellId(override val clientUid: String, override val id: Int) : ComplexId
class RigId(override val clientUid: String, override val id: Int) : ComplexId
class GeneralLookupTypeId(override val clientUid: String, override val id: Int) : ComplexId
class GeneralLookupItemId(override val clientUid: String, override val id: Int) : ComplexId
class CommentId(override val clientUid: String, override val id: Int) : ComplexId
class GeneralLookupLanguageId(override val clientUid: String, override val id: Int) : ComplexId
class ScenarioAttributeValueId(override val clientUid: String, override val id: Int) : ComplexId

interface ComplexId : Serializable {
    val clientUid: String
    val id: Int
}
