package com.example.springjpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class SpringJpaApplication

fun main(args: Array<String>) {
    runApplication<SpringJpaApplication>(*args)
}
