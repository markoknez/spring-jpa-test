package com.example.springjpa

import com.example.springjpa.module.*
import com.example.springjpa.module.repositories.GeneralLookupSystem
import com.example.springjpa.module.repositories.RigRepo
import com.example.springjpa.module.repositories.ScenarioRepo
import com.example.springjpa.module.repositories.WellRepo
import org.h2.tools.Server
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import javax.transaction.Transactional

@SpringBootTest
class AttributeTest {
    companion object {
        @BeforeAll
        @JvmStatic
        fun before() {
            Server.createTcpServer("-tcpPort", "8082").start()
        }
    }

    @Autowired
    private lateinit var scenarioRepo: ScenarioRepo

    @Autowired
    private lateinit var rigRepo: RigRepo

    @Autowired
    private lateinit var wellRepo: WellRepo

    //    @Autowired
//    private lateinit var generalSystemRepos: GeneralLookupSystem.Container
    val GeneralLookupItemId.asScenarioAttributeValueId get() = ScenarioAttributeValueId(this.clientUid, this.id)

    @Test
    @Transactional
    fun doingStuffsWithGeneralLookup() {
//        val well1 = wellRepo.save(Well("well1"))
//        val rig1 = rigRepo.save(Rig("rig1", "desc1", null))
//        val scenario1 = Scenario("scenario1")
//        scenario1.rig = rig1
//        scenario1.well = well1
//        scenarioRepo.save(scenario1)
//        val type = generalSystemRepos.type.save(GeneralLookupType("type1"))
//        val item1 = GeneralLookupItem("item1")
//        val item2 = GeneralLookupItem("item1")
//        item1.typeId = type
//        item2.typeId = type
//        generalSystemRepos.item.save(item1)
//        generalSystemRepos.item.save(item2)
//        val language1 = generalSystemRepos.language.save(GeneralLookupLanguage("lang1", cultureCode = "la-la", isDefault = true))
//        val language2 = generalSystemRepos.language.save(GeneralLookupLanguage("lang2", cultureCode = "la-la1", isDefault = false))
//        val language3 = generalSystemRepos.language.save(GeneralLookupLanguage("lang3", cultureCode = "la-la2", isDefault = false))
//        val value1 = generalSystemRepos.values.save(GeneralLookupValue(item1.id!!, language1.id!!, "item1 in lang1"))
//        val value2 = generalSystemRepos.values.save(GeneralLookupValue(item1.id!!, language2.id!!, "item1 in lang2"))
//        val value3 = generalSystemRepos.values.save(GeneralLookupValue(item1.id!!, language3.id!!, "item1 in lang3"))
//        val value4 = generalSystemRepos.values.save(GeneralLookupValue(item2.id!!, language1.id!!, "item2 in lang1"))
//        scenario1.attributes = mutableSetOf(item1.id!!.asScenarioAttributeValueId, item2.id!!.asScenarioAttributeValueId)
//        scenarioRepo.save(scenario1)
//        println("data: ${scenarioRepo.findById(scenario1.id!!).get().attributes!!.map { it.id }}")
//        // test 1
//        scenario1.attributes = mutableSetOf()
//        println("data: ${scenarioRepo.findById(scenario1.id!!).get().attributes}")
//        scenarioRepo.save(scenario1)
//        println("emptying")
//        scenario1.attributes = mutableSetOf(item2.id!!.asScenarioAttributeValueId)
//        scenarioRepo.save(scenario1)
//        // test 3
//        scenarioRepo.delete(scenario1)
    }

    @Test
    //@Transactional
    fun testingScenarioAttribute() {
        val scenario1 = scenarioRepo.findById(ScenarioId("type-descriptor", 1)).get()
        println("----------------------------------------")
        println(scenario1)
        println("----------------------------------------")
        println(scenario1.attributes)
        println("----------------------------------------")
        println(scenario1.rig!!.id)
        println("----------------------------------------")
        println(scenario1.rig!!.name)
        println("----------------------------------------")
        println(scenario1.wellId)
        println("----------------------------------------")
        println(scenario1.attributes)
        println("----------------------------------------")
        scenario1.attributes += ScenarioAttributeValueId("type-descriptor", 2)
        println("----------------------------------------")
        scenarioRepo.save(scenario1)
        println("----------------------------------------")
        println(scenario1.attributes)
    }

    @Test
    //@Transactional
    fun commentTest(){
        val scenario1 = scenarioRepo.findById(ScenarioId("type-descriptor", 1)).get()
        println(scenario1.comments)
        val c2 = Comment()
        c2.comment = "valami"
        c2.created = Date()
        scenario1.comments += c2
        println("----------------------------------------")
        scenarioRepo.save(scenario1)
        println("----------------------------------------")
    }

}