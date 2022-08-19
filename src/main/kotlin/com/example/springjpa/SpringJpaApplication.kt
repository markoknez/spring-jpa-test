package com.example.springjpa

import org.hibernate.boot.model.TypeContributions
import org.hibernate.boot.model.TypeContributor
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl
import org.hibernate.service.ServiceRegistry
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class SpringJpaApplication {

}

fun main(args: Array<String>) {
    runApplication<SpringJpaApplication>(*args)
}
