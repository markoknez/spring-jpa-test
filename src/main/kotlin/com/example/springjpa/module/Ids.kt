package com.example.springjpa.module

import java.io.Serializable

class ScenarioId(override val clientUid: String, override val id: Int) : ComplexId
class WellId(override val clientUid: String, override val id: Int) : ComplexId
interface ComplexId : Serializable {
    val clientUid: String
    val id: Int
}
