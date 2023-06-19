package com.andrii_a.muze

import com.andrii_a.muze.data.DatabaseFactory
import com.andrii_a.muze.domain.repository.ArtistsRepository
import com.andrii_a.muze.domain.repository.ArtworksRepository
import com.andrii_a.muze.plugins.configureKoin
import com.andrii_a.muze.plugins.configureMonitoring
import com.andrii_a.muze.plugins.configureRouting
import com.andrii_a.muze.plugins.configureSerialization
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureKoin()
    DatabaseFactory.init()

    configureSerialization()
    configureMonitoring()

    val artistsRepository by inject<ArtistsRepository>()
    val artworksRepository by inject<ArtworksRepository>()

    configureRouting(artistsRepository, artworksRepository)
}
