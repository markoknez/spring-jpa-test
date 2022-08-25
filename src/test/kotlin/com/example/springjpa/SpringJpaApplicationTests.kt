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
        // test entity loaded from database
        assert(wellFromDb.single().name == "marko")

        val well1 = Well("without-scenario")
        val well2 = Well("with-scenario")
        wellRepo.saveAll(listOf(well1, well2))
        scenarioRepo.save(Scenario("new-scenario").apply { well = well2 })

        // test entity join on different entity ids (wellId to scenarioId)
        val r1 = wellRepo.wellLeftJoinWithScenarioOnId(WellId("", 1))
        assert(r1 != null)
        val r2 = wellRepo.wellLeftJoinWithScenarioOnId(WellId("", 2))
        assert(r2 != null)

        // test well eager load on manyToOne relationship
        val scenarios = scenarioRepo.findAll()
        assert(scenarios.first().well?.name == "with-scenario")

        // try to join with table which does not have entity
        val result = wellRepo.wellInnerJoinWithTestOnName("with-scenario")
        assert(result.isNotEmpty())

        // try to use nativeQuery with WellId
        val nativeResult = wellRepo.nativeQueryWithComplexId(WellId("", 1))
        assert(nativeResult.isNotEmpty())
    }
}
