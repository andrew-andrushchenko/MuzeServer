package com.andrii_a.muze.plugins

import com.andrii_a.muze.domain.repository.ArtistsRepository
import com.andrii_a.muze.domain.repository.ArtworksRepository
import com.andrii_a.muze.routes.artistsRoute
import com.andrii_a.muze.routes.artworksRoute
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import java.io.File

fun Application.configureRouting(
    artistsRepository: ArtistsRepository,
    artworksRepository: ArtworksRepository
) {

    routing {
        get("/") {
            call.respondText("Welcome to Muze server!")
        }

        artistsRoute(artistsRepository)
        artworksRoute(artworksRepository)

        staticFiles("/", File("/")) {
            preCompressed(CompressedFileType.GZIP)
        }
    }
}
