package com.kotlin.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan("com.example.demo")
@EnableJpaRepositories(basePackages = arrayOf("com.example.demo.repositories"))
@EntityScan("com.example.demo")
class RecipeApplication

fun main(args: Array<String>) {
	runApplication<RecipeApplication>(*args)
}
