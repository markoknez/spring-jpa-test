package com.example.springjpa

import com.example.springjpa.module.ComplexIdJavaTypeDescriptor
import com.example.springjpa.module.ComplexIdSingleColumnType
import com.example.springjpa.module.ScenarioId
import com.example.springjpa.module.WellId
import org.hibernate.boot.model.TypeContributor
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl
import org.hibernate.jpa.boot.spi.TypeContributorList
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class SpringJpaApplication {
    @Bean
    fun customHibernateTypeRegistrar(): HibernatePropertiesCustomizer {
        return HibernatePropertiesCustomizer {
            it[EntityManagerFactoryBuilderImpl.TYPE_CONTRIBUTORS] = TypeContributorList {
                listOf(TypeContributor { typeContributions, _ ->
//                    typeContributions.contributeType(ComplexIdSingleColumnType("complexId", ComplexIdJavaTypeDescriptor(WellId::class.java)))
//                    typeContributions.contributeType(ComplexIdSingleColumnType("scenarioId", ComplexIdJavaTypeDescriptor(ScenarioId::class.java)))
                })
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringJpaApplication>(*args)
}
