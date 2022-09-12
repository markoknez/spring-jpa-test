package com.example.springjpa

import com.example.springjpa.module.*
import com.example.springjpa.module.repositories.*
import org.h2.tools.Server
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.transaction.Transactional


@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
class SpringJpaApplication

fun main(args: Array<String>) {
    Server.createTcpServer("-tcpPort", "9092").start()
    runApplication<SpringJpaApplication>(*args)
}

@Component
@Transactional
class Runner(
    private val wellRepo: WellRepo,
    private val rigRepo: RigRepo,
    private val scenarioRepo: ScenarioRepo,
    private val generalSystemRepos:GeneralLookupSystem.Container,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        //clientsSeparatedByTransaction()
        doingStuffsWithRigAndScenario()
//        testingWithPrefilledData()
    }

    //    fun clientsSeparatedByTransaction() {
//        DataSourceConfig.threadLocalDataSourceKey.set("clientUid-1")
//        TransactionTemplate(transactionManager).execute {
//            val wellc1 = wellRepo.save(Well("c1"))
//            wellc1.name = "new-c1"
//            entityManager.flush()
//        }
//        DataSourceConfig.threadLocalDataSourceKey.set("clientUid-2")
//        TransactionTemplate(transactionManager).execute {
//            val wellc2 = wellRepo.save(Well("c2"))
//            wellc2.name = "new-c2"
//            entityManager.flush()
//        }
//    }

    fun doingStuffsWithRigAndScenario() {
        val well1 = wellRepo.save(Well("well1"))
        val scenario1 = scenarioRepo.save(Scenario("scenario1"))
        scenario1.rig= Rig("rig1", "desc1", null)
        scenario1.well = well1
        scenarioRepo.save(scenario1)
        println(scenario1.rig?.id)
        val desc1 = scenario1.rig?.description
        println(desc1)
    }

//        public val GeneralLookupItemId.asScenarioAttributeValueId get() = ScenarioAttributeValueId(this.clientUid, this.id)
//    fun doingStuffsWithGeneralLookup() {
//        val well1 = wellRepo.save(Well("well1"))
//        val rig1 = rigRepo.save(Rig("rig1", "desc1", null))
//        val scenario1 = scenarioRepo.save(Scenario("scenario1"))
//        scenario1.rig = rig1
//        scenario1.well = well1
//        scenarioRepo.save(scenario1)
//        val type = generalSystemRepos.type.save(GeneralLookupType("type1"))
//        val item1 = generalSystemRepos.item.save(GeneralLookupItem("item1"))
//        val item2 = generalSystemRepos.item.save(GeneralLookupItem("item1"))
//        item1.type = type
//        item2.type = type
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
//   }
}
