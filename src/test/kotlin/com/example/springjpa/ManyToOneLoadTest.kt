package com.example.springjpa

import com.example.springjpa.module.repositories.RigRepo
import com.example.springjpa.module.repositories.ScenarioRepo
import com.example.springjpa.module.repositories.WellRepo
import org.h2.tools.Server
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class ManyToOneLoadTest {
        companion object {
        @BeforeAll
        @JvmStatic
        fun before() {
            Server.createTcpServer("-tcpPort", "9092").start()
        }
    }
    @Autowired
    private lateinit var scenarioRepo: ScenarioRepo
//    @Autowired
//    private lateinit var scenarioRepo2: ScenarioRepo2
    @Autowired
    private lateinit var rigRepo: RigRepo
    @Autowired
    private lateinit var wellRepo: WellRepo

//    @Test
//    @Transactional
//    fun testForLazyLoading(){
//        // when
//        val allScenario = scenarioRepo.findAll()
//        val scenario1 = allScenario.first()
//        val scenario2 = allScenario[1]
//        println("-------------------------------------")
//        println("this select will not trigger a load")
//        println(scenario1.rig?.id)
//        println("-------------------------------------")
//        println("testing equals on lazy load")
//        println(scenario1.equals(scenario2))
//        println("-------------------------------------")
//        println("hashcode before load")
//        // this will interestingly load elementCollection, and only that
//        println(scenario1.hashCode())
//        println("-------------------------------------")
//        println("testing tostring()")
//        // default tostring implementation will trigger a load
//        // default toString only serialize construcot params
//        println(scenario1.toString())
//        println("-------------------------------------")
//        println("this will trigger a load")
//        println(scenario1.rig)
//        println(scenario1.attributes)
//        println("-------------------------------------")
//        println("hashcode after load")
//        println(scenario1.hashCode())
//
//        println("-------------------------------------")
//        println("testing equals() -> should be false")
//        println(scenario1.equals(scenario2))
//        println("testing equals() -> false because only because data class equals depend on the constructor only")
//        scenario2?.name = scenario1.name
//        println(scenario1.equals(scenario2))
//        println("-------------------------------------")
//        println("testing proxies:")
//        val scenario3 = Scenario(id = null, "test-1", rig = scenario1.rig, well =  scenario1.well, attributes = linkedSetOf())
//        val scenario4 = Scenario(scenario1.id, scenario1.name, scenario1.rig, scenario1.well, scenario1.attributes)
//        println(Hibernate.getClass(scenario3))
//        println(scenario4.equals(scenario1))
//        scenario1.name = "akarmi"
//        scenarioRepo.save(scenario1)
//
//        //next line is a minefield
//        scenario1?.rig!!.name = "abc"
//        //scenarioRepo.save(scenario)
//    }

//    @Test
//    @Transactional
//    fun testForLazyLoadingWithAttributes(){
//        // getting a scenario
//        val scenario = scenarioRepo.findAll().firstOrNull()
//        // this is working
//        println(scenarioRepo.findAttributesForScenario(scenario!!.id!!.id))
//        // this is not, and according to
//        println("attributes:${scenario.attributes}")
//    }
    @Test
    fun testWellRepo(){
        val wells = wellRepo.findAll().toList()
        val wells1 = wells.first()
        val wells2 = wells[1]
        println(wells1.toString())
        println(wells1.hashCode())
        println(wells1.equals(wells2))
        println(wells2.hashCode())
    }

//    @Test
//    fun scenarioTest(){
//        val scenario1 = Scenario2(name = "scenario-1-t")
//        val scenario2 = Scenario2(name = "scenario-12-t")
//        scenarioRepo2.save(scenario1)
//        val scenario1copy = scenarioRepo2.findById(scenario1.id!!).get()
//        println(scenario1===scenario1copy)
//        scenario1.name = "scenario-1-t-c"
//        scenario1copy.name = "scenario-1c-t-c"
//        scenarioRepo2.saveAll(listOf(scenario1, scenario1copy))
//        println("-------------------------------------")
//        println(scenario1===scenario1copy)
//        println("-------------------------------------")
//        println(scenarioRepo2.count())
//
//    }
}
