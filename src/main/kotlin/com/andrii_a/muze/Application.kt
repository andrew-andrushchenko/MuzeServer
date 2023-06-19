package com.andrii_a.muze

import io.ktor.server.application.*
import com.andrii_a.muze.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
