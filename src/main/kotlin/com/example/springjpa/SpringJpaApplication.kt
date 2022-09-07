package com.example.springjpa

import com.example.springjpa.module.*
import com.example.springjpa.module.repositories.*
import org.h2.tools.Server
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Component


@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
class SpringJpaApplication

fun main(args: Array<String>) {
    Server.createTcpServer("-tcpPort", "9092").start()
    runApplication<SpringJpaApplication>(*args)
}

@Component
class Runner(
    private val wellRepo: WellRepo,
    private val rigRepo: RigRepo,
    private val scenarioRepo: ScenarioRepo
    /*private val generalLookupTypeRepo: GeneralLookupTypeRepo,
    private val generalLookupItemRepo: GeneralLookupItemRepo,
    private val generalLookupLanguageRepo: GeneralLookupLanguageRepo*/
) : ApplicationRunner {
    @Autowired
    var repos: GeneralLookupSystem.Container? = null
    override fun run(args: ApplicationArguments?) {
        //clientsSeparatedByTransaction()
        //doingStuffsWithRigAndScenario()
        doingStuffsWithGeneralLookup()

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
        val rig1 = rigRepo.save(Rig("rig1", "desc1", null))
        val scenario1 = scenarioRepo.save(Scenario("scenario1"))
        scenario1.rig = rig1
        scenario1.well = well1
        scenarioRepo.save(scenario1)
        println(well1.id)
        println(rig1.id)
        println(scenario1.name)
    }

    fun doingStuffsWithGeneralLookup() {
        val well1 = wellRepo.save(Well("well1"))
        val rig1 = rigRepo.save(Rig("rig1", "desc1", null))
        val scenario1 = scenarioRepo.save(Scenario("scenario1"))
        scenario1.rig = rig1
        scenario1.well = well1
        scenarioRepo.save(scenario1)
        val type = repos!!.type.save(GeneralLookupType("type1"))
        val item = repos!!.item.save(GeneralLookupItem("item1"))
        item.type = type
        repos!!.item.save(item)
        val language = repos!!.language.save(GeneralLookupLanguage("lang1", cultureCode = "la-la", isDefault = true))
        val value = repos!!.values.save(GeneralLookupValue(item.id!!, language.id!!, "item1 in lang1"))
       /*val type = generalLookupTypeRepo.save(GeneralLookupType("type1"))
        val item = generalLookupItemRepo.save(GeneralLookupItem("item1"))
        item.type = type
        generalLookupItemRepo.save(item)*/
    }
}
