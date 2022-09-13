package com.example.springjpa

import com.example.springjpa.module.repositories.RigRepo
import com.example.springjpa.module.repositories.ScenarioRepo
import com.example.springjpa.module.repositories.WellRepo
import org.h2.tools.Server
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

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
    @Autowired
    private lateinit var rigRepo: RigRepo
    @Autowired
    private lateinit var wellRepo: WellRepo

    @Test
    @Transactional
    fun testForLazyLoading(){
        // when
        val scenario = scenarioRepo.findAll().firstOrNull()
        // this will not trigger a load
        println(scenario?.rig?.id)
        // this will trigger a load
        println(scenario?.rig)
        //next line is a minefield
        scenario?.rig!!.name = "abc"
        //scenarioRepo.save(scenario)
    }

    @Test
    @Transactional
    fun testForLazyLoadingWithAttributes(){
        // getting a scenario
        val scenario = scenarioRepo.findAll().firstOrNull()
        // this is working
        println(scenarioRepo.findAttributesForScenario(scenario!!.id!!.id))
        // this is not, and according to
        println("attributes:${scenario.attributes}")
    }
}
