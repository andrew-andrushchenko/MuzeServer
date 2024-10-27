package com.andrii_a.muze

import com.andrii_a.muze.data.configureDatabase
import com.andrii_a.muze.domain.repository.ArtistsRepository
import com.andrii_a.muze.domain.repository.ArtworksRepository
import com.andrii_a.muze.plugins.configureKoin
import com.andrii_a.muze.plugins.configureMonitoring
import com.andrii_a.muze.plugins.configureRouting
import com.andrii_a.muze.plugins.configureSerialization
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureSerialization()
    configureMonitoring()

    val hikariDataSource by inject<HikariDataSource>()
    configureDatabase(hikariDataSource)

    val artistsRepository by inject<ArtistsRepository>()
    val artworksRepository by inject<ArtworksRepository>()

    configureRouting(artistsRepository, artworksRepository)
}
