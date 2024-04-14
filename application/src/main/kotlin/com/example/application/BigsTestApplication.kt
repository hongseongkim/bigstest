package com.example.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.example.internal.repository"])
@EntityScan(basePackages = ["com.example.domain.entity"])
@ComponentScan(basePackages = ["com.example"])
class BigsTestApplication

fun main(args: Array<String>) {
	runApplication<BigsTestApplication>(*args)
}