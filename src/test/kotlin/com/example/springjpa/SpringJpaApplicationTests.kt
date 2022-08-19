package com.example.springjpa

import com.example.springjpa.module.*
import org.h2.tools.Server
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean

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
    fun contextLoads() {
        val well = Well(ComplexId("teste", 15), "marko")
        wellRepo.save(well)

        val scenario = Scenario(ComplexId("adfasdf", 22), "new-scenario", well)

        scenarioRepo.save(scenario)

    }

}
