package com.example.springjpa

import com.example.springjpa.module.Well
import com.example.springjpa.module.config.DataSourceConfig
import com.example.springjpa.module.repositories.WellRepo
import org.h2.tools.Server
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionTemplate
import javax.persistence.EntityManager


@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
class SpringJpaApplication

fun main(args: Array<String>) {
    Server.createTcpServer("-tcpPort", "9092").start()
    runApplication<SpringJpaApplication>(*args)
}

@Component
@Transactional
class Runner(
    private val transactionManager: PlatformTransactionManager,
    private val entityManager: EntityManager,
    private val wellRepo: WellRepo
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        clientsSeparatedByTransaction()
    }

    fun clientsSeparatedByTransaction() {
        DataSourceConfig.threadLocalDataSourceKey.set("clientUid-1")
        TransactionTemplate(transactionManager).execute {
            val wellc1 = wellRepo.save(Well("c1"))
            wellc1.name = "new-c1"
            entityManager.flush()
        }
        DataSourceConfig.threadLocalDataSourceKey.set("clientUid-2")
        TransactionTemplate(transactionManager).execute {
            val wellc2 = wellRepo.save(Well("c2"))
            wellc2.name = "new-c2"
            entityManager.flush()
        }
    }
}
