package com.andrii_a.muze

import com.andrii_a.muze.di.testAppModule
import com.andrii_a.muze.domain.repository.ArtistsRepository
import com.andrii_a.muze.domain.repository.ArtworksRepository
import com.andrii_a.muze.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            install(Koin) {
                slf4jLogger()
                modules(testAppModule)
            }

            val artistsRepository by inject<ArtistsRepository>()
            val artworksRepository by inject<ArtworksRepository>()

            configureRouting(artistsRepository, artworksRepository)
        }

        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Welcome to Muze server!", bodyAsText())
        }
    }
}
