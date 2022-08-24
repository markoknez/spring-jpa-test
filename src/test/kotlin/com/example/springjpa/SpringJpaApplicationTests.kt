package com.example.springjpa

import com.example.springjpa.module.*
import com.example.springjpa.module.repositories.ScenarioRepo
import com.example.springjpa.module.repositories.WellRepo
import org.h2.tools.Server
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql

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
    @Sql("/test.sql")
    fun contextLoads() {
        val wellFromDb = wellRepo.findAll()
        val well1 = Well("without-scenario")
        val well2 = Well("with-scenario")
        wellRepo.saveAll(listOf(well1, well2))
        scenarioRepo.save(Scenario("new-scenario").apply { well = well2 })

        val r1 = wellRepo.funnyGetById(WellId("", 1))
        val r2 = wellRepo.funnyGetById(WellId("", 2))
    }

}
