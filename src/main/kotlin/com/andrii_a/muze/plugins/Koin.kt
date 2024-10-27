package com.andrii_a.muze.plugins

import com.andrii_a.muze.di.artistsModule
import com.andrii_a.muze.di.artworksModule
import com.andrii_a.muze.di.databaseModule
import com.andrii_a.muze.di.dateTimeModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(databaseModule, dateTimeModule, artistsModule, artworksModule)
    }
}