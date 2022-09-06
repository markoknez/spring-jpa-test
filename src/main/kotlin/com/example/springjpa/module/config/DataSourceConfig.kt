package com.example.springjpa.module.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import javax.sql.DataSource

@Configuration
class DataSourceConfig {
    companion object {
        val threadLocalDataSourceKey = ThreadLocal<String>().apply {
            set("clientUid-1")
        }
    }

    @Bean
    fun getMeDataSource(): DataSource {
        val dataSource = object : AbstractRoutingDataSource() {
            override fun determineCurrentLookupKey(): Any {
                return threadLocalDataSourceKey.get()
            }
        }
        val ddlSQL = this::class.java.getResourceAsStream("/schema.sql")!!.reader().readText()

        dataSource.setTargetDataSources(
            mapOf(
                "clientUid-1" to "jdbc:h2:mem:test-1;DB_CLOSE_DELAY=100000",
                "clientUid-2" to "jdbc:h2:mem:test-2;DB_CLOSE_DELAY=100000"
            ).entries
                .associate {
                    it.key to HikariDataSource().apply {
                        jdbcUrl = it.value
                        connection.use { c -> c.prepareStatement(ddlSQL).use { p -> p.executeUpdate() } }
                    }

                }
        )

        return dataSource
    }
}
