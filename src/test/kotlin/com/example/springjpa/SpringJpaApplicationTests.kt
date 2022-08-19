package com.example.springjpa

import com.example.springjpa.module.*
import org.h2.tools.Server
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class SpringJpaApplicationTests {
    companion object {
        @BeforeAll
        @JvmStatic
        fun before() {
            Server.createTcpServer("-tcpPort", "9092").start()
        }
    }

    @Autowired
    private lateinit var wellRepo: WellRepo

    @Autowired
    private lateinit var scenarioRepo: ScenarioRepo

    @Test
    @Transactional
    fun contextLoads() {
        val well = Well(null, "marko")
        wellRepo.save(well)
        val scenarioId = ComplexId("adfasdf", 22)
        scenarioRepo.save(Scenario(scenarioId, "new-scenario", well))

        assert(scenarioRepo.findByIdOrNull(scenarioId)!!.well?.id?.id == 1)
        val wellFromRepo = wellRepo.findByIdOrNull(ComplexId("", 1))!!
        wellFromRepo.scenarios.size == 1
    }

}
