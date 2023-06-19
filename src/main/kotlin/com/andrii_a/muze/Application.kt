package com.andrii_a.muze

import com.andrii_a.muze.data.DatabaseFactory
import com.andrii_a.muze.plugins.configureKoin
import com.andrii_a.muze.plugins.configureMonitoring
import com.andrii_a.muze.plugins.configureRouting
import com.andrii_a.muze.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureKoin()
    DatabaseFactory.init()

    configureSerialization()
    configureMonitoring()
    configureRouting()
}
