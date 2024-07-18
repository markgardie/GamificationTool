package com.kpi.gamificationtool

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class GamificationToolApplication

fun main(args: Array<String>) {
    runApplication<GamificationToolApplication>(*args)
}
